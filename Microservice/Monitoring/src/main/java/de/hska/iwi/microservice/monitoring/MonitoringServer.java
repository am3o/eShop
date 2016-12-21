package de.hska.iwi.microservice.monitoring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by ameo on 21.12.16.
 */
@EnableHystrixDashboard
@SpringBootApplication
public class MonitoringServer {
    public static void main(String[] args) {
        SpringApplication.run(MonitoringServer.class, args);
    }
}
