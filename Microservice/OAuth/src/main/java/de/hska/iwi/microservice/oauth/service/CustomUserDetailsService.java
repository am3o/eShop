package de.hska.iwi.microservice.oauth.service;

import de.hska.iwi.microservice.oauth.client.AuthenticationServiceClient;
import de.hska.iwi.microservice.oauth.client.api.AuthenticationService;
import de.hska.iwi.microservice.oauth.client.api.ClientService;
import de.hska.iwi.microservice.oauth.entity.CustomerCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ameo on 21.01.17.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private static final String AUTHENTICATION_SERVICE = "authentication-service";

  private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
  private final DiscoveryClient discoveryClient;

  @Autowired
  public CustomUserDetailsService(
      DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  private ClientService clientServiceSetup() {
    ClientService service = null;
    if (discoveryClient.getServices().contains(AUTHENTICATION_SERVICE)) {
      service = new AuthenticationServiceClient(
          discoveryClient.getInstances(AUTHENTICATION_SERVICE).get(0).getUri());
    }
    return service;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    ClientService service = clientServiceSetup();
    AuthenticationService authenticationService = new AuthenticationServiceClient(service.getURI());
    CustomerCredential userModelDetails = authenticationService.existCustomer(username);

    if (!(userModelDetails instanceof CustomerCredential)) {
      throw new UsernameNotFoundException(username);
    }

    return userModelDetails;
  }
}
