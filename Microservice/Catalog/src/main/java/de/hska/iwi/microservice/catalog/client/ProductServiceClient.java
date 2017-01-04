package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.ProductService;
import de.hska.iwi.microservice.catalog.entity.Product;
import java.net.URI;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by ameo on 13.11.16.
 */
public class ProductServiceClient extends AbstractClientService implements ProductService {

  private static final Logger logger = Logger.getLogger(ProductServiceClient.class);

  private RestTemplate restClient = new RestTemplate();

  public ProductServiceClient(URI serviceUrl) {
    super(serviceUrl);
  }

  @Override
  public List<Product> getProducts(int categoryId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .queryParam("categoryId", categoryId);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    ResponseEntity<List> result = restClient.exchange(uriBuilder.build().encode().toUri(),
        HttpMethod.GET,
        entity,
        List.class);
    return result.getBody();
  }

  @Override
  public Product getProduct(int id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .path(String.valueOf(id));

    HttpEntity<String> entity = new HttpEntity(headers);

    ResponseEntity<Product> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.GET,
            entity,
            Product.class);
    return responseEntity.getBody();
  }

  @Override
  public List<Product> search(String details, double minPrice, double maxPrice) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .path("search")
        .queryParam("details", details)
        .queryParam("minPrice", minPrice)
        .queryParam("maxPrice", maxPrice);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    ResponseEntity<List> result = restClient.exchange(uriBuilder.build().encode().toUri(),
        HttpMethod.GET,
        entity,
        List.class);
    return result.getBody();
  }

  @Override
  public Product createProduct(Product product) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI());

    HttpEntity<String> entity = new HttpEntity(product, headers);

    ResponseEntity<Product> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.POST,
            entity,
            Product.class);
    return responseEntity.getBody();
  }

  @Override
  public Product updateProduct(int id, Product product) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .path(String.valueOf(id));

    HttpEntity<String> entity = new HttpEntity(headers);

    ResponseEntity<Product> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.PUT,
            entity,
            Product.class);
    return responseEntity.getBody();
  }

  @Override
  public Boolean deleteProduct(int id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .path(String.valueOf(id));

    HttpEntity<String> entity = new HttpEntity(headers);

    ResponseEntity<Product> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.DELETE,
            entity,
            Product.class);

    return !(this.getProduct(id) instanceof Product);
  }
}