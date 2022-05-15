package com.shamanthaka.rl.service;

import com.shamanthaka.rl.config.DroolsConfig;
import com.shamanthaka.rl.model.Order;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    public int getDiscount(Order order){
        DroolsConfig droolsConfig = new DroolsConfig();
        KieSession session = droolsConfig.getKieSession("rules/offer.drl");
        session.addEventListener(new DebugRuleRuntimeEventListener());
        session.addEventListener(new DebugAgendaEventListener());

        session.insert(order);
        session.fireAllRules();
        session.dispose();
        return order.getDiscount();
    }
}
