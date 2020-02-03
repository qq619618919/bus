package com.paic.gbd.client.impl;

import com.paic.gbd.client.GbdBusClient;
import com.paic.gbd.client.auth.Account;
import com.paic.gbd.client.http.HttpConfig;

import java.util.List;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/29
 * @Description: client.impl
 * @version: 1.0
 */
public class GbdBusClientJsonImpl extends AbstractGbdBusClient {

    public GbdBusClientJsonImpl(String endpoint, Account account, HttpConfig httpConfig, String userAgent) {
        super(endpoint, account, httpConfig, userAgent);
    }

    @Override
    public List<String> getTopics() {
        List<String> topicNames = callWrapper(getService().getTopics());
        return topicNames;
    }

    @Override
    public void putRecords() {

    }
}
