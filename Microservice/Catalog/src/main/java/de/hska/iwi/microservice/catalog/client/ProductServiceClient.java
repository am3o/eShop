package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.ProductService;
import de.hska.iwi.microservice.catalog.entity.Product;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

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
        return restClient.getForObject(String.format("%s/?categoryId=%d", serviceUrl, categoryId), List.class);
    }

    @Override
    public Product getProduct(int id) {
        return restClient.getForObject(String.format("%s/%d", serviceUrl, id), Product.class);
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
    public boolean deleteProduct(int id) {
        restClient.delete(String.format("%s/%d", serviceUrl, id));
        return !(this.getProduct(id) instanceof Product);
    }
}