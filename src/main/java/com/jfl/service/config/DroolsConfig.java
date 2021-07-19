package com.jfl.service.config;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Configuration
public class DroolsConfig {

    private static final String RULES_PATH = "rules/";

    @Bean
    public KieServices kieServices(){
        log.info("KieServices created......");
        return KieServices.Factory.get();
    }


    @Bean
    public KieFileSystem kieFileSystem(KieServices kieServices) throws IOException {
        log.info("KieFileSystem created......");
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
        log.info("Drool files {}",resources.length);
        for(Resource resource:resources) {
            log.info(resource.getFilename());
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH+resource.getFilename(),"UTF-8"));
        }
        return kieFileSystem;
    }

    @Bean
    public KieContainer kieContainer(KieFileSystem kieFileSystem,KieServices kieServices) {
        log.info("KieContainer created......");
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession kieSession(KieContainer kieContainer) {
        log.info("KieSession created......");
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.addEventListener(new RuleRuntimeEventListener() {
            @Override
            public void objectInserted(ObjectInsertedEvent event) {
                log.info("Object inserted \n "
                        + event.getObject().toString());
            }

            @Override
            public void objectUpdated(ObjectUpdatedEvent event) {
                log.info("Object was updated \n"
                        + "New Content \n"
                        + event.getObject().toString());
            }

            @Override
            public void objectDeleted(ObjectDeletedEvent event) {
                log.info("Object retracted \n"
                        + event.getOldObject().toString());
            }
        });
        return kieSession;
    }

    public static class OutputDisplay {

        public void showText(String text) {
            System.out.println("==================================================");
            System.out.println("Text: " + text + ",  date: " + LocalDate.now());
            System.out.println("==================================================");
        }

    }
}
