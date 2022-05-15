package com.shamanthaka.rl.controller;


import com.shamanthaka.rl.model.Order;
import com.shamanthaka.rl.service.OfferService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcartOfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/order")
    public int orderNow(@RequestBody Order order){
        return offerService.getDiscount(order);
    }
}
