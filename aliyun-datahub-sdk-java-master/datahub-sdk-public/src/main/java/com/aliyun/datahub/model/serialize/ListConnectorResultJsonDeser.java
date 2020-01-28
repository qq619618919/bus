package com.aliyun.datahub.model.serialize;

import com.aliyun.datahub.common.transport.Response;
import com.aliyun.datahub.common.util.JacksonParser;
import com.aliyun.datahub.exception.DatahubServiceException;
import com.aliyun.datahub.model.ListConnectorRequest;
import com.aliyun.datahub.model.ListConnectorResult;
import com.aliyun.datahub.rest.DatahubHttpHeaders;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

@Deprecated
public class ListConnectorResultJsonDeser implements Deserializer<ListConnectorResult,ListConnectorRequest,Response> {
    @Override
    public ListConnectorResult deserialize(ListConnectorRequest request, Response response) throws DatahubServiceException {
        if (!response.isOK()) {
            throw JsonErrorParser.getInstance().parse(response);
        }

        ListConnectorResult rs = new ListConnectorResult();
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
        JsonNode node = tree.get("Connectors");
        if (node != null && !node.isNull()) {
            if (node.isArray()) {
                Iterator<JsonNode> it = node.elements();
                while (it.hasNext()) {
                    JsonNode connector = it.next();
                    rs.addConnector(connector.asText());
                }
            }
        }
        return rs;
    }

    private ListConnectorResultJsonDeser() {

    }

    private static ListConnectorResultJsonDeser instance;

    public static ListConnectorResultJsonDeser getInstance() {
        if (instance == null)
            instance = new ListConnectorResultJsonDeser();
        return instance;
    }
}
