package com.shamanthka.rl.service;

import com.shamanthka.rl.model.Product;
import com.shamanthka.rl.config.MyKafkaConfiguration;
import com.shamanthka.rl.model.ProductVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

@Component
public class ProductSubscriber {


    private MyKafkaConfiguration config;

    @Autowired //constructor injection
    public ProductSubscriber(MyKafkaConfiguration config){
        this.config = config;
        Properties props = new Properties();
        props.put("bootstrap.servers", config.getConsumerBootstrap());
        props.put("key.deserializer",config.getConsumerKeyDeSer());
        props.put("value.deserializer", config.getConsumerValDeSer());
        props.put("group.id", config.getGroupId());
        this.topicName = config.getTopicName();
        consumer = new KafkaConsumer<>(props);
    }
    private String topicName;

    private KafkaConsumer<String, ProductVO> consumer;



    public void consume(){

        this.consumer.subscribe(Arrays.asList(topicName));
        //consumer.seekToBeginning();
        try{
            while(true){
                ConsumerRecords<String, ProductVO> records = consumer.poll(100);
                for(ConsumerRecord<String, ProductVO> rec: records){
                    System.out.println(" reading from topic product info " + rec.value().getId() +", " + rec.value().getModelNumber() + ", "
                            + rec.value().getName() +", " + rec.value().getQuantity() + ", " + rec.value().getBrandId());
                    System.out.println(" value, key, offset, partition and topic " + rec.value() +", " + rec.key() + ", "
                            + rec.offset() + ", " + rec.partition() + ", " + rec.topic() );

                    //sending emails to consumers
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            this.consumer.close();
        }
    }

}
