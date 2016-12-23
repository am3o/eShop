package de.hska.iwi.microservice.catalog.client.api;

/**
 * Created by ameo on 26.11.16.
 */
public interface AuthenticationService {
    /**
     * Überprüft ob der angegebene Benutzer existiert.
     */
    Boolean existCustomer(String username, String password, boolean permission);
}
