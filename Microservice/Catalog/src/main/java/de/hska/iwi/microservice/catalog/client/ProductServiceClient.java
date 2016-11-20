package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.ProductService;
import de.hska.iwi.microservice.catalog.entity.Product;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public class ProductServiceClient implements ProductService{
    private static final Logger logger = Logger.getLogger(ProductServiceClient.class);

    private final ProductService service;

    public ProductServiceClient(String productServiceUrl) {
        this.service = Feign.builder()
                .contract(new JAXRSContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(ProductService.class, productServiceUrl);
    }

    @Override
    public Product createProduct(Product product) {
        return service.createProduct(product);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return service.deleteProduct(id);
    }

    @Override
    public List<Product> getProducts(int categoryId) {
        return service.getProducts(categoryId);
    }

    @Override
    public Product getProduct(int id) {
        return service.getProduct(id);
    }
}
