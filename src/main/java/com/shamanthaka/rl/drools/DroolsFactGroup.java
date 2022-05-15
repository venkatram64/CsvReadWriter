package com.shamanthaka.rl.drools;


import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class DroolsFactGroup {

    private static KieServices ks = KieServices.Factory.get();
    private static final String RULES_PRODUCT_DRL = "rules/myRule4.drl";

    public static void main(String[] args) {
        DroolsFactGroup fact = new DroolsFactGroup();
        fact.executeDrools();
    }

    public void executeDrools(){

        KieContainer kieContainer = getKieContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Product2 product = new Product2();
        product.setType("gold");
        //product.setEvent("sale");

        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println("Discount from Rule engine: " + product.getDiscount());

    }

    public KieContainer getKieContainer(){

        KieFileSystem kieFileSystem = ks.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PRODUCT_DRL));
        KieBuilder kb = ks.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = ks.newKieContainer(kieModule.getReleaseId());
        return kieContainer;

    }
}
