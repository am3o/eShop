package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.AuthenticationService;
import de.hska.iwi.microservice.catalog.entity.Credential;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ameo on 26.11.16.
 */
public class AuthenticationServiceClient implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceClient.class);
    private static final String PERMISSION_ADMIN = "Admin";

    private final String serviceUrl;

    private final RestTemplate restClient = new RestTemplate();

    public AuthenticationServiceClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public Boolean existCustomer(String username, String password, boolean permission) {
        URI destUri = null;
        try {
            destUri = new URI(serviceUrl);
        } catch (URISyntaxException ex) {
            logger.error("Fehler beim auflösen der URI", ex);

        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("usr", username);
        headers.add("pass", password);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(destUri).queryParam("permission", permission);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try{
            ResponseEntity<Credential> responseEntity = restClient.exchange(uriBuilder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    Credential.class);
            Credential responseCredential = responseEntity.getBody();
            if(responseCredential.getUsername() instanceof String &&
                    responseCredential.getPassword() instanceof String)
                return responseCredential.getRole().equals(PERMISSION_ADMIN);
        }catch (RestClientException ex) {
            logger.error("Fehler bei Anfrage an Authentication-Service", ex);
        }
        return false;
    }
}
