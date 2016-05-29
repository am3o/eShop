package de.hska.iwi.microservice.product.config;

import de.hska.iwi.microservice.product.ProductServer;
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
public class ProductServerConfiguration {

    private final Logger logger = Logger.getLogger(ProductServerConfiguration.class);

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();

        try (InputStream input = ProductServer.class.getClassLoader().getResourceAsStream("db-config.properties")) {
            final Properties properties = new Properties();
            properties.load(input);

            dataSource.setDriverClassName(properties.getProperty("spring.jpa.driver_class"));
            dataSource.setUrl(properties.getProperty("spring.jpa.url"));
            dataSource.setUsername(properties.getProperty("spring.jpa.username"));
            dataSource.setPassword(properties.getProperty("spring.jpa.password"));

        } catch (FileNotFoundException e) {
            logger.error("Could not find database configuration file.", e);
        } catch (IOException e) {
            logger.error("Could not read database configuration file.", e);
        }

        return dataSource;
    }
}