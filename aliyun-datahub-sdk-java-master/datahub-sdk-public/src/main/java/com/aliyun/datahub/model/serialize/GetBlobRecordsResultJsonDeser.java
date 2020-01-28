package com.aliyun.datahub.model.serialize;

import com.aliyun.datahub.common.transport.Response;
import com.aliyun.datahub.common.util.JacksonParser;
import com.aliyun.datahub.exception.DatahubClientException;
import com.aliyun.datahub.exception.DatahubServiceException;
import com.aliyun.datahub.model.BlobRecordEntry;
import com.aliyun.datahub.model.GetBlobRecordsRequest;
import com.aliyun.datahub.model.GetBlobRecordsResult;
import com.aliyun.datahub.rest.DatahubHttpHeaders;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class GetBlobRecordsResultJsonDeser implements Deserializer<GetBlobRecordsResult, GetBlobRecordsRequest, Response> {
    @Override
    public GetBlobRecordsResult deserialize(GetBlobRecordsRequest request, Response response) throws DatahubClientException {
        if (!response.isOK()) {
            throw JsonErrorParser.getInstance().parse(response);
        }

        GetBlobRecordsResult rs = new GetBlobRecordsResult();
        rs.setRequestId(response.getHeader(DatahubHttpHeaders.HEADER_DATAHUB_REQUEST_ID));

        ObjectMapper mapper = JacksonParser.getObjectMapper();

        JsonNode tree = null;
        try {
            tree = mapper.readTree(response.getBody());
        } catch (IOException e) {
            throw new DatahubServiceException(
                    "JsonParseError", "Parse body failed:" + response.getBody(), response);
        }

        rs.setNextCursor(tree.get("NextCursor").asText());
        long sequence = tree.get("StartSeq").asLong();
        rs.setStartSeq(sequence);
        JsonNode recordsNode = tree.get("Records");

        List<BlobRecordEntry> records = new ArrayList<BlobRecordEntry>();
        Iterator<JsonNode> itRecord = recordsNode.elements();
        while (itRecord.hasNext()) {
            BlobRecordEntry entry = new BlobRecordEntry();
            JsonNode record = itRecord.next();
            JsonNode time = record.get("SystemTime");
            JsonNode data = record.get("Data");
            JsonNode attrs = record.get("Attributes");

            // sequence
            entry.setSequence(sequence++);

            // time
            entry.setSystemTime(time.asLong());

            // shard id
            entry.setShardId(request.getShardId());

            try {
                // record data
                entry.setData(data.binaryValue());
            } catch (IOException e) {
                throw new DatahubClientException("Decode record data failed:" + e.getMessage(), e);
            }

            // attributes
            if (attrs != null && !attrs.isNull()) {
                Iterator<Map.Entry<String, JsonNode>> itAttr = attrs.fields();
                Map<String, String> attrMap = new HashMap<String, String>();
                while (itAttr.hasNext()) {
                    Map.Entry<String, JsonNode> attr = itAttr.next();
                    entry.putAttribute(attr.getKey(), attr.getValue().asText());
                }
            }
            records.add(entry);
        }

        rs.setRecords(records);
        return rs;
    }

    private GetBlobRecordsResultJsonDeser() {

    }

    private static GetBlobRecordsResultJsonDeser instance;

    public static GetBlobRecordsResultJsonDeser getInstance() {
        if (instance == null)
            instance = new GetBlobRecordsResultJsonDeser();
        return instance;
    }
}
