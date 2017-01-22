package de.hska.iwi.microservice.oauth.client;

import de.hska.iwi.microservice.oauth.client.api.ClientService;
import java.net.URI;

/**
 * Created by ameo on 21.01.17.
 */
public class AbstractClientService implements ClientService {

  protected URI serviceURL;

  public AbstractClientService(URI serviceURL) {
    super();
    this.serviceURL = serviceURL;
  }

  public void setServiceURL(URI serviceURL) {
    this.serviceURL = serviceURL;
  }

  @Override
  public URI getURI() {
    return serviceURL;
  }
}
