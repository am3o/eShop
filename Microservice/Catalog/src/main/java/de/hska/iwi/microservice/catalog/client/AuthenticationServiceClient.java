package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ameo on 26.11.16.
 */
public class AuthenticationServiceClient implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceClient.class);

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
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("usr", username);
        headers.add("pass", password);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(destUri).queryParam("permission", permission);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Boolean> result = restClient.exchange(uriBuilder.build().encode().toUri(),
                                                                HttpMethod.GET,
                                                                entity,
                                                                Boolean.class);
        return result.getBody();
    }
}
