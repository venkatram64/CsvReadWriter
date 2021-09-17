package com.shamanthaka.rl.service;

import com.shamanthaka.rl.config.MyKafkaConfiguration;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
//Finding topic wise message count
public class MessageCount {

    private Properties props = null;

    private MyKafkaConfiguration config;

    @Autowired //constructor injection
    public MessageCount(MyKafkaConfiguration config){
        this.config = config;
        props = new Properties();
        props.put("bootstrap.servers", config.getConsumerBootstrap());
        props.put("key.deserializer",config.getConsumerKeyDeSer());
        props.put("value.deserializer", config.getConsumerValDeSer());
        props.put("group.id", config.getGroupId());
    }

    public void processMessageCount() throws Exception{
        AdminClient adminClient = AdminClient.create(props);
        KafkaFuture<Set<String>> names = adminClient.listTopics().names();
        Set<String> topicList = names.get();
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        Iterator<String> iter = topicList.iterator();
        while(iter.hasNext()){
            String topicName = iter.next();
            String[] topicNameArray = {topicName};
            DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicNameArray));
            Map<String, KafkaFuture<TopicDescription>> futureMap = result.values();
            KafkaFuture<TopicDescription> topicDescription = futureMap.get(topicName);
            TopicDescription topicDes = topicDescription.get();
            int partitionSize = topicDes.partitions().size();
            long earliestOffset = 0L;
            long endOffset = 0L;
            long numberOfMessages = 0L;

            for(int partition = 0; partition < partitionSize; partition++){
                TopicPartition topicPartition = new TopicPartition(topicName, partition);
                kafkaConsumer.assign(Collections.singleton(topicPartition));
                kafkaConsumer.poll(0L);
                kafkaConsumer.seekToBeginning(Collections.singletonList(topicPartition));
                earliestOffset = kafkaConsumer.position(topicPartition) - 1L;
                kafkaConsumer.seekToEnd(Collections.singletonList(topicPartition));
                endOffset = kafkaConsumer.position(topicPartition) - 1L;
                numberOfMessages += endOffset - earliestOffset;
            }
            System.out.println("TOPIC: " + topicName + " Earliest Offset: " + earliestOffset +
                    "Latest Offset: " + endOffset + ", Total messages: " + numberOfMessages);
        }
        kafkaConsumer.close();
        adminClient.close();
    }
}
