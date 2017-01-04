package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.ClientService;
import java.net.URI;

/**
 * Created by ameo on 03.01.17.
 */
public class AbstractClientService implements ClientService {

  private final URI serviceURL;

  public AbstractClientService(URI serviceURL) {
    super();
    this.serviceURL = serviceURL;
  }

  @Override
  public final URI getURI() {
    return serviceURL;
  }
}
