package com.shamanthaka.rl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "kafka-local-destination-settings")
public class MyKafkaConfiguration {

    private String producerBootstrap;
    private String producerKeySer;
    private String producerValSer;
    private String acks;
    private String topicName;

    private String consumerBootstrap;
    private String consumerKeyDeSer;
    private String consumerValDeSer;
    private String groupId;

    public MyKafkaConfiguration(){}

    public String getProducerBootstrap() {
        return producerBootstrap;
    }

    public void setProducerBootstrap(String producerBootstrap) {
        this.producerBootstrap = producerBootstrap;
    }

    public String getProducerKeySer() {
        return producerKeySer;
    }

    public void setProducerKeySer(String producerKeySer) {
        this.producerKeySer = producerKeySer;
    }

    public String getProducerValSer() {
        return producerValSer;
    }

    public void setProducerValSer(String producerValSer) {
        this.producerValSer = producerValSer;
    }

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getConsumerBootstrap() {
        return consumerBootstrap;
    }

    public void setConsumerBootstrap(String consumerBootstrap) {
        this.consumerBootstrap = consumerBootstrap;
    }

    public String getConsumerKeyDeSer() {
        return consumerKeyDeSer;
    }

    public void setConsumerKeyDeSer(String consumerKeyDeSer) {
        this.consumerKeyDeSer = consumerKeyDeSer;
    }

    public String getConsumerValDeSer() {
        return consumerValDeSer;
    }

    public void setConsumerValDeSer(String consumerValDeSer) {
        this.consumerValDeSer = consumerValDeSer;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
