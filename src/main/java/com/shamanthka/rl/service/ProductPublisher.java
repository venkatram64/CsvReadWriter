package com.shamanthka.rl.service;

import com.shamanthka.rl.model.Product;
import com.shamanthka.rl.config.MyKafkaConfiguration;
import com.shamanthka.rl.model.ProductVO;
import org.apache.kafka.clients.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Component
public class ProductPublisher {


    private MyKafkaConfiguration config;

    /*//@Autowired
    public void setConfig(MyKafkaConfiguration config) {
        this.config = config;
    }
*/
    @Autowired  //constructor injection
    public ProductPublisher(MyKafkaConfiguration config){
        this.config = config;
        Properties props = new Properties();
        props.put("bootstrap.servers", config.getProducerBootstrap());
        props.put("key.serializer",config.getProducerKeySer());
        props.put("value.serializer", config.getProducerValSer());
        props.put("acks", config.getAcks()); //for acknowledgement 0, 1, all
        this.topicName = config.getTopicName();
        producer = new KafkaProducer<>(props);
    }
    private String topicName;

    private Producer<String, ProductVO> producer;


    public void publishProduct(ProductVO product){
        try{
            this.producer.send(new ProducerRecord<String, ProductVO>(topicName, product.getId() + "", product),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            if(e != null){
                                e.printStackTrace();
                            }
                            System.out.println("publishing to topic completed..." + product.getId());
                        }
                    });
            Thread.sleep(100);

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            this.producer.flush();
            //this.producer.close();
        }
    }

}
