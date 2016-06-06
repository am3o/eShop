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

package de.hska.iwi.microservice.authentication.config;

import de.hska.iwi.microservice.authentication.AuthenticationServer;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@EnableAutoConfiguration
@PropertySource("classpath:db-config.properties")
public class AuthenticationServerConfig {
    private final Logger logger = Logger.getLogger(AuthenticationServerConfig.class);

    public AuthenticationServerConfig() {
        super();
        logger.info("Authentication-Konfiguration wurde geladen.");
    }

    @Bean
    public DataSource dataSource() {
        logger.info("Datenquelle wurde erzeugt.");
        DataSource dataSource = new DataSource();

        String filename = "db-config.properties";
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = AuthenticationServer.class.getClassLoader().getResourceAsStream(filename);
            properties.load(input);

            dataSource.setDriverClassName(properties.getProperty("spring.jpa.driver_class"));
            dataSource.setUrl(properties.getProperty("spring.jpa.url"));
            dataSource.setUsername(properties.getProperty("spring.jpa.username"));
            dataSource.setPassword(properties.getProperty("spring.jpa.password"));
        } catch (FileNotFoundException e) {
            logger.error("Datenbank-Konfigurationsdatei nicht gefunden", e);
        } catch (IOException e) {
            logger.error("Lesen der Datenbank-Konfigurationsdatei fehlgeschlagen", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Schließen des Input-Streams fehlgeschlagen.");
                }
            }
        }
        return dataSource;
    }
}
