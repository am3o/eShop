/*
 *    Copyright (c) 2016.   Joshua Braun
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package de.hska.iwi.microservice.product;

import de.hska.iwi.microservice.product.config.ProductServerConfiguration;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Created by ameo on 25.05.16.
 */
@EnableDiscoveryClient
@SpringBootApplication
@Import(ProductServerConfiguration.class)
public class ProductServer {
    private static Logger logger = Logger.getLogger(ProductServer.class);

    public static void main(String[] args) {
        logger.info("Erzeuge Product-Service");
        System.setProperty("spring.config.name", "Product-Server");
        SpringApplication.run(ProductServer.class, args);
    }
}
