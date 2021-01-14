package com.shamanthka.rl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ProductDeserializer implements Deserializer<ProductVO> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public ProductVO deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null) {
                System.out.println("Null recieved at deserialize ");
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            ProductVO prod = mapper.readValue(bytes, ProductVO.class);

            return prod;
        }catch (Exception ex){
            throw new SerializationException("Error when deserializing byte[] to Product");
        }
    }

    @Override
    public void close() {

    }
}
