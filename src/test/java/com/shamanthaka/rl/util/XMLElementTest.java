package com.shamanthaka.rl.util;

import com.shamanthaka.rl.model.ProductXml;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class XMLElementTest {

    private XMLElement productXml = new XMLElement();

    @Test
    public void convertRowIntoXmlDataTest(){
        List<ProductXml> xmls = productXml.convertRowIntoXmlData();
        for(ProductXml x: xmls) {
            System.out.println(x.getXmlRow());
        }
        assertTrue(xmls.size() > 0);
    }

    /*
    <?xml version="1.0" encoding="UTF-8"?>
    <Products>
       <id>1</id>
       <brand_id>2</brand_id>
       <model_number>123</model_number>
       <product_name>phone</product_name>
       <quantity>20</quantity>
    </Products>
     */

}