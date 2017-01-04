package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.CategoryService;
import de.hska.iwi.microservice.catalog.entity.Category;
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
public class CategoryServiceClient extends AbstractClientService implements CategoryService {

  private static final Logger logger = Logger.getLogger(CategoryServiceClient.class);

  private RestTemplate restClient = new RestTemplate();

  public CategoryServiceClient(URI serviceUrl) {
    super(serviceUrl);
  }

  @Override
  public List<Category> getAllCategories() {
    logger.info("Liefere alle Kategorien zurück.");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI());

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    ResponseEntity<List> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.GET,
            entity,
            List.class);
    return responseEntity.getBody();
  }

  @Override
  public Category createCategory(Category category) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI());

    HttpEntity<String> entity = new HttpEntity(category, headers);

    ResponseEntity<Category> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.POST,
            entity,
            Category.class);
    return responseEntity.getBody();
  }

  @Override
  public Category updateCategory(Category category) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .path(String.valueOf(category.getId()));

    HttpEntity<String> entity = new HttpEntity(category, headers);

    ResponseEntity<Category> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.PUT,
            entity,
            Category.class);
    return responseEntity.getBody();
  }

  @Override
  public Category getCategory(int categoryId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .path(String.valueOf(categoryId));

    HttpEntity<String> entity = new HttpEntity(headers);

    ResponseEntity<Category> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.GET,
            entity,
            Category.class);
    return responseEntity.getBody();
  }

  @Override
  public Boolean deleteCategory(int categoryId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(this.getURI())
        .path(String.valueOf(categoryId));

    HttpEntity<String> entity = new HttpEntity(headers);

    ResponseEntity<Category> responseEntity = restClient
        .exchange(uriBuilder.build().encode().toUri(),
            HttpMethod.DELETE,
            entity,
            Category.class);
    return !(this.getCategory(categoryId) instanceof Category);
  }
}
