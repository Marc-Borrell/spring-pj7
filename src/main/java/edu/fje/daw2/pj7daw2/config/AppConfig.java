package edu.fje.daw2.pj7daw2.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestClient;

/**
 * Configuració de beans de l'aplicació.
 * Defineix el client REST per a crides externes i la connexió manual a MongoDB.
 *
 * @author Grup1
 * @version 1.0
 */
@Configuration
public class AppConfig {

    /**
     * Crea un bean de RestClient per a fer crides HTTP a l'API d'Aqicn.
     *
     * @return instància de RestClient
     */
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;


    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    /**
     * Crea un bean de MongoClient amb autenticació explícita.
     *
     * @return instància de MongoClient
     */
    @Bean
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .build();
        return MongoClients.create(settings);
    }
    /**
     * Crea un bean de MongoTemplate per a operacions amb MongoDB.
     *
     * @param mongoClient client de MongoDB
     * @return instància de MongoTemplate
     */
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, mongoDatabase);
    }
}