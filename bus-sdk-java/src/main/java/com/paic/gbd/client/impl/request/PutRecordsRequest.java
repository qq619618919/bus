package com.paic.gbd.client.impl.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/29
 * @Description: client.impl.request
 * @version: 1.0
 */
public class PutRecordsRequest {
    @JsonProperty("Records")
    private List<?> records;
}
