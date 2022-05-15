package com.shamanthaka.rl.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class DroolsConfig {

    private static KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getFileSystem(String drlFile){
        KieFileSystem fileSystem = kieServices.newKieFileSystem();
        fileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        return fileSystem;
    }

    private void setKieRepository(){
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }


    public KieContainer getKieContainer(String drlFile) {
        //setKieRepository();
        KieBuilder kieBuilder = kieServices.newKieBuilder(getFileSystem(drlFile));
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }


    public KieSession getKieSession(String drlFile){
        return getKieContainer(drlFile).newKieSession();
    }
}
