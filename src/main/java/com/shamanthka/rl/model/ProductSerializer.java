package com.shamanthka.rl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ProductSerializer implements Serializer<ProductVO> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String s, ProductVO product) {
        try{
            if(product == null){
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] retVal = objectMapper.writeValueAsString(product).getBytes();
            return retVal;

        }catch (Exception e){
            throw new SerializationException("Error when serializing Employee to byte array");
        }
    }

    @Override
    public void close() {
    }
}
