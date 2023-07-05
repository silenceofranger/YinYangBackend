package com.yinyang.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Collections;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(externalMongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//        converter.setCustomConversions(
//                new MongoCustomConversions(Collections.singletonList(new MongoDateToJodaDateTime())));
        converter.afterPropertiesSet();
        return new MongoTemplate(externalMongoDbFactory(), converter);
    }

    @Bean
    public MongoDatabaseFactory externalMongoDbFactory() throws Exception {
        return new SimpleMongoClientDatabaseFactory(mongoUri);
    }

}
