package org.reactive.service.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.SpringDataMongoDB;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Data
@Slf4j
@Configuration
@EnableMongoAuditing
@EnableReactiveMongoRepositories(basePackages = "org.reactive.service.mongo.repo")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    private final MongoProperties mongoProperties;

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        log.info("Reactive MongoClient initialized");
        log.info("Mongo Host {} ",mongoProperties.getHost());
        log.info("Mongo Port {} ",mongoProperties.getPort());
        log.info("Mongo Username {} ",mongoProperties.getUsername());
        log.info("Mongo Password {} ",mongoProperties.getPassword());
        log.info("Mongo Database {} ",mongoProperties.getDatabase());
        log.info("Mongo Authentication Database {} ",mongoProperties.getAuthenticationDatabase());
        ConnectionString connectionString=new ConnectionString("mongodb://"+mongoProperties.getHost()+":"+mongoProperties.getPort());
        log.info("Mongo Uri {}",connectionString.toString());
        MongoClientSettings mongoClientSettings=MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings,SpringDataMongoDB.driverInformation());
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDbFactory() {
        log.info("Reactive MongoDatabaseFactory initialized");
        return new SimpleReactiveMongoDatabaseFactory(this.reactiveMongoClient(), this.getDatabaseName());
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(ReactiveMongoDatabaseFactory databaseFactory, MappingMongoConverter mongoConverter) {
        log.info("Reactive MongoTemplate initialized");
        return new ReactiveMongoTemplate(databaseFactory, mongoConverter);
    }
}
