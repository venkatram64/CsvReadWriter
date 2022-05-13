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
        //getting topic list
        AdminClient adminClient = AdminClient.create(props);
        KafkaFuture<Set<String>> names = adminClient.listTopics().names();
        Set<String> topicList = names.get();

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);

        Iterator<String> iter = topicList.iterator();
        //for topic wise
        while(iter.hasNext()){
            String topicName = iter.next();
            String[] topicNameArray = {topicName};
            DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicNameArray));
            Map<String, KafkaFuture<TopicDescription>> futureMap = result.values();
            KafkaFuture<TopicDescription> topicDescription = futureMap.get(topicName);
            TopicDescription topicDes = topicDescription.get();
            //getting the partition size of the topic
            int partitionSize = topicDes.partitions().size();
            long earliestOffset = 0L;
            long endOffset = 0L;
            long numberOfMessages = 0L;
            //finding messages for each partition
            for(int partition = 0; partition < partitionSize; partition++){
                //creating topic partition
                TopicPartition topicPartition = new TopicPartition(topicName, partition);
                //assign topic partition into kafkaConsumer
                kafkaConsumer.assign(Collections.singleton(topicPartition));
                //polling
                kafkaConsumer.poll(0L);
                //read from beginning
                kafkaConsumer.seekToBeginning(Collections.singletonList(topicPartition));
                //finding the offset
                earliestOffset = kafkaConsumer.position(topicPartition) - 1L;
                //read upto the end of the topic
                kafkaConsumer.seekToEnd(Collections.singletonList(topicPartition));
                //find the end offset
                endOffset = kafkaConsumer.position(topicPartition) - 1L;

                numberOfMessages += endOffset - earliestOffset;
            }
            System.out.println("TOPIC: " + topicName + " Earliest Offset: " + earliestOffset +
                    "Latest Offset: " + endOffset + ", Total messages: " + numberOfMessages);
        }
        kafkaConsumer.close();
        adminClient.close();
    }

    public static void main(String[] args) throws Exception {
        MyKafkaConfiguration config = new MyKafkaConfiguration();
        MessageCount messageCount = new MessageCount(config);
        messageCount.processMessageCount();
    }
}
