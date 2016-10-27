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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@EnableAutoConfiguration
@PropertySource("classpath:db-config.properties")
public class AuthenticationServerConfig {
    private static final String DB_CONFIG_FILE = "db-config.properties";
    private final Logger logger = Logger.getLogger(AuthenticationServerConfig.class);

    public AuthenticationServerConfig() {
        super();
        logger.info("Authentication-Konfiguration wurde geladen.");
    }

    @Bean
    public DataSource dataSource() {
        logger.info("Datenquelle wird erzeugt.");
        DataSource dataSource = new DataSource();

        Properties properties = new Properties();
        InputStream input = null;

        try {
            logger.info("Datenquelle wird parametrisiert.");
            input = AuthenticationServer.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
            properties.load(input);

            dataSource.setDriverClassName(properties.getProperty("spring.jpa.driver_class"));
            dataSource.setUrl(properties.getProperty("spring.jpa.url"));
            dataSource.setUsername(properties.getProperty("spring.jpa.username"));
            dataSource.setPassword(properties.getProperty("spring.jpa.password"));
            logger.info("Datenquelle wurde erfolgreich parametrisiert.");
        } catch (FileNotFoundException e) {
            logger.error("Datenbank-Konfigurationsdatei nicht gefunden", e);
        } catch (IOException e) {
            logger.error("Lesen der Datenbank-Konfigurationsdatei fehlgeschlagen", e);
        }catch (Exception e) {
            logger.error("Ein Fehler hat sich zugetragen beim erzeugen der Datenquelle.", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Schließen des Input-Streams fehlgeschlagen.");
                }
            }
        }

        logger.info("Datenquelle wurde erfolgreich erzeugt.");
        return dataSource;
    }

    @Bean
    @Autowired
    public EntityManagerFactory entityManagerFactory(DataSource dataSource){
        Properties properties = new Properties();
        InputStream input = null;

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        try{
            logger.info("EntityManagerFactory wird parametrisiert.");
            input = AuthenticationServer.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
            properties.load(input);

            vendorAdapter.setGenerateDdl(true);
            vendorAdapter.setDatabase(Database.MYSQL);
            vendorAdapter.setDatabasePlatform(properties.getProperty("spring.jpa.hibernate.dialect"));

            factory.setJpaVendorAdapter(vendorAdapter);
            factory.setPackagesToScan("de.hska.iwi.microservice.authentication.domian");
            factory.setDataSource(dataSource);
            factory.afterPropertiesSet();
        } catch (IOException e) {
            logger.error("Datenbank-Konfigurationsdatei wurde nicht gefunden", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Schließen des Input-Streams fehlgeschlagen.");
                }
            }
        }

        logger.info("EntityManagerFactory wurde erfolgreich erzeugt.");
        return factory.getObject();
    }
}
