package de.hska.iwi.microservice.catalog.service;

import de.hska.iwi.microservice.catalog.client.CategoryServiceClient;
import de.hska.iwi.microservice.catalog.client.ProductServiceClient;
import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
@Service
public class CatalogServiceFacade implements ICatalogServiceFacade{
    private final Logger logger = Logger.getLogger(CatalogServiceFacade.class);

    private static final String CATEGORY_SERVICE = "Category-Service";
    private static final String PRODUCT_SERVICE = "production-service";

    private CategoryServiceClient categoryServiceClient;
    private ProductServiceClient productServiceClient;

    @Autowired
    public CatalogServiceFacade(DiscoveryClient discoveryClient) {
        try{
            String categoryServiceUrl = discoveryClient.getInstances(CATEGORY_SERVICE).iterator().next().getUri().toString();
            this.categoryServiceClient = new CategoryServiceClient(categoryServiceUrl);

            String productServiceUrl = discoveryClient.getInstances(PRODUCT_SERVICE).iterator().next().getUri().toString();
            this.productServiceClient = new ProductServiceClient(productServiceUrl);
        }catch (Exception ex) {
            logger.error("Initialisierungsfehler", ex);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryServiceClient.getAllCategories();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryServiceClient.createCategory(category);
    }

    @Override
    public Category updateCategory(int categoryId, Category category) {
        return null;
    }

    @Override
    public Category getCategory(int categoryId) {
        return categoryServiceClient.getCategory(categoryId);
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        return categoryServiceClient.deleteCategory(categoryId);
    }

    @Override
    public Product createProduct(Product product) {
        return productServiceClient.createProduct(product);
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return productServiceClient.deleteProduct(id);
    }

    @Override
    public List<Product> getProducts() {
        return productServiceClient.getProducts();
    }

    @Override
    public Product getProduct(int id) {
        return productServiceClient.getProduct(id);
    }

    @Override
    public List<Product> getProductsByCategorieId(int id) {
        return productServiceClient.getProductsByCategorieId(id);
    }

    @Override
    public List<Catalog> getCatalog() {
        return null;
    }

    @Override
    public List<Catalog> getCatalogCategorie(int categoryId) {
        return null;
    }

    @Override
    public List<Catalog> searchCatalog(float minPrice, float maxPrice, String content) {
        return null;
    }
}
