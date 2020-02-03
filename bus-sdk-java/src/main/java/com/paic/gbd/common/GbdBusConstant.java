package com.paic.gbd.common;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.HashMap;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/30
 * @Description: com.paic.gbd.common
 * @version: 1.0
 */
public class GbdBusConstant {
    public static final String X_GBDBUS_SECURITY_TOKEN = "x-gbdbus-security-token";
    public static final String GBDBUS_DATETIME_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";

    public static void main(String[] args) {
        HashMap<String, Object> map = Maps.newHashMap();
        AdminClient adminClient = KafkaAdminClient.create(map);
        KafkaProducer producer = new KafkaProducer(map);
        KafkaConsumer consumer = new KafkaConsumer(map);
    }
}
