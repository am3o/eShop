package de.hska.iwi.microservice.catalog;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ameo on 13.11.16.
 */
@EnableDiscoveryClient
@RibbonClient(name = "CatalogService")
@SpringBootApplication
public class CatalogServer {
    private static Logger logger = Logger.getLogger(CatalogServer.class);

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        logger.info("Erzeuge Catalog-Service.");
        SpringApplication.run(CatalogServer.class, args);
    }
}
