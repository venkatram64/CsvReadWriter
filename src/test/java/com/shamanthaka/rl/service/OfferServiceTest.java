package com.shamanthaka.rl.service;


import com.shamanthaka.rl.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OfferServiceTest {

    @Autowired
    private OfferService offerService;

    @Test
    public void offerTest(){
        Order order = new Order();
        order.setCardType("HDFC");
        order.setPrice(5001);
        int discount = offerService.getDiscount(order);
        System.out.println(discount);
    }

}
