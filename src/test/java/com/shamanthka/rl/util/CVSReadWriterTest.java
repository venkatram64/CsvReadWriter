package com.shamanthka.rl.util;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CVSReadWriterTest {

    @Autowired
    private CVSReadWriter readWriter;

    @Test
    public void readWriteTest(){
        readWriter.readAndWrite();
        //printing the products from kafka topic
        readWriter.readingFromTopic();
    }

}