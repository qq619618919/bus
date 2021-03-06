package com.aliyun.datahub.model.serialize;

import com.aliyun.datahub.common.transport.Response;
import com.aliyun.datahub.common.util.JacksonParser;
import com.aliyun.datahub.exception.DatahubServiceException;
import com.aliyun.datahub.model.ErrorEntry;
import com.aliyun.datahub.model.PutBlobRecordsRequest;
import com.aliyun.datahub.model.PutBlobRecordsResult;
import com.aliyun.datahub.rest.DatahubHttpHeaders;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

public class PutBlobRecordsResultJsonDeser implements Deserializer<PutBlobRecordsResult, PutBlobRecordsRequest, Response> {

    @Override
    public PutBlobRecordsResult deserialize(PutBlobRecordsRequest request, Response response) throws DatahubServiceException {
        if (!response.isOK()) {
            throw JsonErrorParser.getInstance().parse(response);
        }

        PutBlobRecordsResult rs = new PutBlobRecordsResult();
        rs.setRequestId(response.getHeader(DatahubHttpHeaders.HEADER_DATAHUB_REQUEST_ID));

        ByteArrayInputStream is = new ByteArrayInputStream(response.getBody());
        ObjectMapper mapper = JacksonParser.getObjectMapper();
        JsonNode tree = null;
        try {
            tree = mapper.readTree(is);
        } catch (IOException e) {
            throw new DatahubServiceException(
                    "JsonParseError", "Parse body failed:" + response.getBody(), response);
        }
        JsonNode node = tree.get("FailedRecordCount");
        if (node != null && !node.isNull()) {
            if (node.isInt()) {
                rs.setFailedRecordCount(node.asInt());
            }
        }

        node = tree.get("FailedRecords");
        if (node != null && !node.isNull()) {
            if (!node.isArray()) {
                throw new DatahubServiceException(
                        "InvalidResultFormat", "Invalid result format:" + tree.asText(), response);
            }

            Iterator<JsonNode> failedNodes = node.elements();
            while (failedNodes.hasNext()) {
                JsonNode failedNode = failedNodes.next();
                int index = failedNode.get("Index").asInt();
                String errCode = failedNode.get("ErrorCode").asText();
                String errMsg = failedNode.get("ErrorMessage").asText();
                ErrorEntry error = new ErrorEntry(errCode, errMsg);
                rs.addFailedIndex(index);
                rs.addFailedError(error);
            }
        }
        return rs;
    }

    private static PutBlobRecordsResultJsonDeser instance;

    private PutBlobRecordsResultJsonDeser() {

    }

    public static PutBlobRecordsResultJsonDeser getInstance() {
        if (instance == null)
            instance = new PutBlobRecordsResultJsonDeser();
        return instance;
    }
}
