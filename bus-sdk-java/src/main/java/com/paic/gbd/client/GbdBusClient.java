package com.paic.gbd.client;

import java.util.List;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/29
 * @Description: client
 * @version: 1.0
 */
public interface GbdBusClient {
    List<String> getTopics();

    void putRecords();
}
