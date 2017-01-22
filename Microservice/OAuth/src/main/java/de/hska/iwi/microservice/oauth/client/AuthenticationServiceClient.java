package de.hska.iwi.microservice.oauth.client;

import de.hska.iwi.microservice.oauth.client.api.AuthenticationService;
import de.hska.iwi.microservice.oauth.entity.CustomerCredential;
import java.net.URI;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by ameo on 21.01.17.
 */
public class AuthenticationServiceClient extends AbstractClientService implements
    AuthenticationService {

  private final static Logger logger = Logger.getLogger(AuthenticationServiceClient.class);
  private final RestTemplate restClient = new RestTemplate();

  public AuthenticationServiceClient(URI serviceURL) {
    super(serviceURL);
  }

  @Override
  public CustomerCredential existCustomer(String username) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("usr", username);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI()).path("username");

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    try {
      ResponseEntity<CustomerCredential> responseEntity = restClient
          .exchange(uriBuilder.build().encode().toUri(),
              HttpMethod.GET,
              entity,
              CustomerCredential.class);
      return responseEntity.getBody();
    } catch (RestClientException ex) {
      logger.error("Fehler bei Anfrage an Authentication-Service", ex);
    }
    return new CustomerCredential();
  }
}
