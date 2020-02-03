package com.paic.gbd.client.auth;

import okhttp3.Request;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/30
 * @Description: com.paic.gbd.client.auth
 * @version: 1.0
 */
public interface Account {
    void addAuthHeaders(Request.Builder reqBuilder);
}
