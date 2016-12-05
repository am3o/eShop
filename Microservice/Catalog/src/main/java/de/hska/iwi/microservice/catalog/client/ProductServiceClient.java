package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.ProductService;
import de.hska.iwi.microservice.catalog.entity.Product;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public class ProductServiceClient implements ProductService {
    private static final Logger logger = Logger.getLogger(ProductServiceClient.class);

    private final String serviceUrl;
    private final RestTemplate restClient = new RestTemplate();

    public ProductServiceClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public List<Product> getProducts(int categoryId) {
        URI destUri = null;
        try {
            destUri = new URI(serviceUrl);
        } catch (URISyntaxException ex) {
            logger.error("Fehler beim auflösen der URI", ex);

        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(destUri).queryParam("categoryId", categoryId);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<List> result = restClient.exchange(uriBuilder.build().encode().toUri(),
                                                            HttpMethod.GET,
                                                            entity,
                                                            List.class);
        return result.getBody();
    }

    @Override
    public Product getProduct(int id) {
        return restClient.getForObject(String.format("%s/%d", serviceUrl, id), Product.class);
    }

    @Override
    public List<Product> search(String details, double minPrice, double maxPrice) {
        URI destUri = null;
        try {
            destUri = new URI(serviceUrl);
        } catch (URISyntaxException ex) {
            logger.error("Fehler beim auflösen der URI", ex);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(destUri)
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
        return restClient.postForEntity(String.format("%s/", serviceUrl),
                                            product,
                                            Product.class).getBody();
    }

    @Override
    public Product updateProduct(int id, Product product) {
        restClient.put(String.format("%s/%d", serviceUrl, id),
                                product);
        return this.getProduct(id);
    }

    @Override
    public Boolean deleteProduct(int id) {
        Boolean isDeleted = false;
        restClient.delete(String.format("%s/%d", serviceUrl, id));
        try{
            isDeleted = !(this.getProduct(id) instanceof Product);
        } catch (Exception ex) {
            isDeleted = true;
        }
        return isDeleted;
    }
}