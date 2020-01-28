package com.aliyun.datahub.model.serialize;

import com.aliyun.datahub.common.transport.Response;
import com.aliyun.datahub.common.util.JacksonParser;
import com.aliyun.datahub.exception.DatahubServiceException;
import com.aliyun.datahub.model.CreateSubscriptionRequest;
import com.aliyun.datahub.model.CreateSubscriptionResult;
import com.aliyun.datahub.rest.DatahubHttpHeaders;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CreateSubscriptionResultJsonDeser implements Deserializer<CreateSubscriptionResult, CreateSubscriptionRequest, Response> {
    @Override
    public CreateSubscriptionResult deserialize(CreateSubscriptionRequest request, Response response) throws DatahubServiceException {
        if (!response.isOK()) {
            throw JsonErrorParser.getInstance().parse(response);
        }

        CreateSubscriptionResult rs = new CreateSubscriptionResult();
        rs.setRequestId(response.getHeader(DatahubHttpHeaders.HEADER_DATAHUB_REQUEST_ID));

        ObjectMapper mapper = JacksonParser.getObjectMapper();
        JsonNode tree = null;
        try {
            tree = mapper.readTree(response.getBody());
        } catch (IOException e) {
            throw new DatahubServiceException(
                    "JsonParseError", "Parse body failed:" + response.getBody(), response);
        }

        rs.setSubId(tree.get("SubId").asText());

        return rs;
    }

    private CreateSubscriptionResultJsonDeser() {}

    private static CreateSubscriptionResultJsonDeser instance;

    public static CreateSubscriptionResultJsonDeser getInstance() {
        if (instance == null) {
            instance = new CreateSubscriptionResultJsonDeser();
        }
        return instance;
    }
}
