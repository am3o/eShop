package de.hska.iwi.microservice.product.config;

import de.hska.iwi.microservice.product.ProductServer;
import de.hska.iwi.microservice.product.domain.ProductRepository;
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
public class ProductServerConfiguration {
    private static final String DB_CONFIG_FILE = "db-config.properties";

    private final Logger logger = Logger.getLogger(ProductServerConfiguration.class);

    public ProductServerConfiguration() {
        super();
        logger.info("Product-Konfiguration wurde geladen.");
    }

    @Bean
    public DataSource dataSource() {
        logger.info("Datenquelle wird erzeugt.");
        DataSource dataSource = new DataSource();

        Properties properties = new Properties();
        InputStream input = null;

        try {
            logger.info("Datenquelle wird parametrisiert.");
            input = ProductServer.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
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
            input = ProductServer.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
            properties.load(input);

            vendorAdapter.setGenerateDdl(true);
            vendorAdapter.setDatabase(Database.MYSQL);
            vendorAdapter.setDatabasePlatform(properties.getProperty("spring.jpa.hibernate.dialect"));

            factory.setJpaVendorAdapter(vendorAdapter);
            factory.setPackagesToScan(ProductRepository.class.getPackage().getName());
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