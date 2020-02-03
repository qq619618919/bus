package com.paic.gbd.client.impl;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/29
 * @Description: client.impl
 * @version: 1.0
 */
public interface GbdBusService {
    @GET("topics")
    Call<List<String>> getTopics();

    @POST("/topics/{topicName}")
    void putRecords(@Path("topicName") String topicName, @Body String aa);
}
