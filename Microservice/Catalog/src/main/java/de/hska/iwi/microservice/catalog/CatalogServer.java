package de.hska.iwi.microservice.catalog;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by ameo on 13.11.16.
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@SpringBootApplication
public class CatalogServer {
    private static Logger logger = Logger.getLogger(CatalogServer.class);

    public static void main(String[] args) {
        logger.info("Erzeuge Catalog-Service.");
        SpringApplication.run(CatalogServer.class, args);
    }
}
