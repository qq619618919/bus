package com.aliyun.datahub.model.serialize;

import com.aliyun.datahub.DatahubConstants;
import com.aliyun.datahub.common.transport.DefaultRequest;
import com.aliyun.datahub.common.transport.HttpMethod;
import com.aliyun.datahub.common.util.JacksonParser;
import com.aliyun.datahub.exception.DatahubClientException;
import com.aliyun.datahub.model.GetConnectorShardStatusRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

@Deprecated
public class GetConnectorShardStatusRequestJsonSer implements Serializer<DefaultRequest, GetConnectorShardStatusRequest> {
    @Override
    public DefaultRequest serialize(GetConnectorShardStatusRequest request) throws DatahubClientException {
        DefaultRequest req = new DefaultRequest();
        req.setResource("/projects/" + request.getProjectName()
                + "/topics/" + request.getTopicName()
                + "/connectors/" + request.getConnectorType().toString().toLowerCase()
        );
        req.setHttpMethod(HttpMethod.POST);

        ObjectMapper mapper = JacksonParser.getObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        node.put("Action", "status");
        node.put(DatahubConstants.ShardId, request.getShardId());
        try {
            req.setBody(mapper.writeValueAsString(node));
        } catch (IOException e) {
            throw new DatahubClientException("serialize error", e);
        }
        return req;
    }

    private GetConnectorShardStatusRequestJsonSer() {

    }

    private static GetConnectorShardStatusRequestJsonSer instance;

    public static GetConnectorShardStatusRequestJsonSer getInstance() {
        if (instance == null)
            instance = new GetConnectorShardStatusRequestJsonSer();
        return instance;
    }
}
