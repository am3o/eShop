package de.hska.iwi.microservice.oauth.client.api;

import de.hska.iwi.microservice.oauth.entity.CustomerCredential;

/**
 * Created by ameo on 21.01.17.
 */
public interface AuthenticationService {

  /**
   * Liefert einen Benutzer anhand des Benutzernamens zurück.
   */
  CustomerCredential existCustomer(String username);
}
