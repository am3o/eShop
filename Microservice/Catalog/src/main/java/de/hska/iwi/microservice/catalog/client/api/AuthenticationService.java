package de.hska.iwi.microservice.catalog.client.api;

import org.springframework.http.ResponseEntity;

/**
 * Created by ameo on 26.11.16.
 */
public interface AuthenticationService {
    /**
     * Überprüft ob der angegebene Benutzer existiert.
     */
    Boolean existCustomer(String username, String password);
}
