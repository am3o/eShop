package de.hska.iwi.microservice.catalog.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.microservice.catalog.client.AuthenticationServiceClient;
import de.hska.iwi.microservice.catalog.client.CategoryServiceClient;
import de.hska.iwi.microservice.catalog.client.ProductServiceClient;
import de.hska.iwi.microservice.catalog.client.api.ClientService;
import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Credential;
import de.hska.iwi.microservice.catalog.entity.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;

/**
 * Created by ameo on 13.11.16.
 */
@Service
public class CatalogServiceFacade implements ICatalogServiceFacade{
    private final Logger logger = Logger.getLogger(CatalogServiceFacade.class);

    private static final String CATEGORY_SERVICE = "category-Service";
    private static final String PRODUCT_SERVICE = "production-service";
    private static final String AUTHENTICATION_SERVICE = "AUTHENTICATION-SERVICE";

    private static final String CATEGORY_SERVICE_URL_DEFAULT = "http://localhost:4211/";
    private static final String PRODUCT_SERVICE_URL_DEFAULT = "http://localhost:4205/";
    private static final String AUTHENTICATION_SERVICE_URL_DEFAULT = "http://localhost:4201/";

    private final DiscoveryClient discoveryClient;

    private CategoryServiceClient categoryServiceClient;
    private ProductServiceClient productServiceClient;
    private AuthenticationServiceClient authenticationServiceClient;

    @Autowired
    public CatalogServiceFacade(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private ClientService setUpClientService (DiscoveryClient discoveryClient, String servicename) {
        ClientService service = null;
        try {
            if(discoveryClient instanceof DiscoveryClient) {
                switch (servicename) {
                    case AUTHENTICATION_SERVICE:
                        if(discoveryClient.getServices().contains(servicename))
                            service = new AuthenticationServiceClient(discoveryClient.getInstances(servicename).get(0).getUri().toString());
                        else
                            service = new AuthenticationServiceClient(AUTHENTICATION_SERVICE_URL_DEFAULT);
                        break;
                    case PRODUCT_SERVICE:
                        if(discoveryClient.getServices().contains(servicename))
                            service = new ProductServiceClient(discoveryClient.getInstances(servicename).get(0).getUri().toString());
                        else
                            service = new ProductServiceClient(PRODUCT_SERVICE_URL_DEFAULT);
                        break;
                    case CATEGORY_SERVICE:
                        if(discoveryClient.getServices().contains(servicename))
                            service = new CategoryServiceClient(discoveryClient.getInstances(servicename).get(0).getUri().toString());
                        else
                            service = new CategoryServiceClient(CATEGORY_SERVICE_URL_DEFAULT);
                        break;
                    default:
                        service = null;
                }
            }
        }catch (Exception ex) {
            logger.error("Initialisierungsfehler: ClientService", ex);
        } finally {
            return service;
        }
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultCheckPermission")
    public boolean checkPermission(Credential credential) {
        logger.info("Überprüfe gegebene Anmeldeinformationen");
        authenticationServiceClient = (AuthenticationServiceClient) setUpClientService(discoveryClient, AUTHENTICATION_SERVICE);
        return authenticationServiceClient.existCustomer(credential.getUsername(), credential.getPassword(), true);
    }

    public boolean defaultCheckPermission(Credential credential){
        logger.warn("AuthentifizierungService nicht verfügbar Standardrückgabe");
        return false;
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultGetAllCategories")
    public List<Category> getAllCategories() {
        categoryServiceClient = (CategoryServiceClient) setUpClientService(discoveryClient, CATEGORY_SERVICE);
        return categoryServiceClient.getAllCategories();
    }

    public List<Category> defaultGetAllCategories() {
        logger.warn("KategorienService nicht verfügbar Standardrückgabe");
        return new ArrayList<>();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultCreateCategory")
    public Category createCategory(Category category) {
        categoryServiceClient = (CategoryServiceClient) setUpClientService(discoveryClient, CATEGORY_SERVICE);
        return categoryServiceClient.createCategory(category);
    }

    public Category defaultCreateCategory(Category category) {
        logger.warn("KategorienService nicht verfügbar Standardrückgabe");
        return new Category();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultUpdateCategory")
    public Category updateCategory(int categoryId, Category category) {
        categoryServiceClient = (CategoryServiceClient) setUpClientService(discoveryClient, CATEGORY_SERVICE);
        return categoryServiceClient.updateCategory(category);
    }

    public Category defaultUpdateCategory(int categoryId, Category category) {
        logger.warn("KategorienService nicht verfügbar Standardrückgabe");
        return category;
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultGetCategory")
    public Category getCategory(int categoryId) {
        categoryServiceClient = (CategoryServiceClient) setUpClientService(discoveryClient, CATEGORY_SERVICE);
        return categoryServiceClient.getCategory(categoryId);
    }

    public Category defaultGetCategory(int categoryId) {
        logger.warn("KategorienService nicht verfügbar Standardrückgabe");
        return new Category();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultDeleteCategory")
    public boolean deleteCategory(int categoryId) {
        List products = this.getProducts(categoryId);
        for (Object item: products) {
            this.deleteProduct(Integer.valueOf((Integer) LinkedHashMap.class.cast(item).get("id")));
        }
        categoryServiceClient = (CategoryServiceClient) setUpClientService(discoveryClient, CATEGORY_SERVICE);
        return categoryServiceClient.deleteCategory(categoryId);
    }

    public boolean defaultDeleteCategory(int categoryId) {
        logger.warn("KategorienService nicht verfügbar Standardrückgabe");
        return false;
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultCreateProduct")
    public Product createProduct(Product product) {
        productServiceClient = (ProductServiceClient) setUpClientService(discoveryClient, PRODUCT_SERVICE);
        return productServiceClient.createProduct(product);
    }

    public Product defaultCreateProduct(Product product) {
        logger.warn("ProduktService nicht verfügbar Standardrückgabe");
        return new Product();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultUpdateProduct")
    public Product updateProduct(int productId, Product product) {
        productServiceClient = (ProductServiceClient) setUpClientService(discoveryClient, PRODUCT_SERVICE);
        return productServiceClient.updateProduct(productId, product);
    }

    public Product defaultUpdateProduct(int productId, Product product) {
        logger.warn("ProduktService nicht verfügbar Standardrückgabe");
        return product;
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultDeleteProduct")
    public boolean deleteProduct(int id) {
        productServiceClient = (ProductServiceClient) setUpClientService(discoveryClient, PRODUCT_SERVICE);
        return productServiceClient.deleteProduct(id);
    }

    public boolean defaultDeleteProduct(int id) {
        logger.warn("ProduktService nicht verfügbar Standardrückgabe");
        return false;
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultGetProducts")
    public List<Product> getProducts(int categoryId) {
        productServiceClient = (ProductServiceClient) setUpClientService(discoveryClient, PRODUCT_SERVICE);
        return productServiceClient.getProducts(categoryId);
    }

    public List<Product> defaultGetProducts(int categoryId) {
        logger.warn("ProduktService nicht verfügbar Standardrückgabe");
        return new ArrayList<>();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultGetProduct")
    public Product getProduct(int id) {
        productServiceClient = (ProductServiceClient) setUpClientService(discoveryClient, PRODUCT_SERVICE);
        return productServiceClient.getProduct(id);
    }

    public Product defaultGetProduct(int id) {
        logger.warn("ProduktService nicht verfügbar Standardrückgabe");
        return new Product();
    }

    @Override
    @HystrixCommand
    public List<Catalog> getCatalog() {
        return null;
    }

    @Override
    @HystrixCommand
    public List<Catalog> getCatalogCategorie(int categoryId) {
        return null;
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultSearchCatalog")
    public List<Product> searchCatalog(double minPrice, double maxPrice, String content) {
        productServiceClient = (ProductServiceClient) setUpClientService(discoveryClient, PRODUCT_SERVICE);
        return productServiceClient.search(content, minPrice, maxPrice);
    }

    public List<Product> defaultSearchCatalog(double minPrice, double maxPrice, String content) {
        logger.warn("ProduktService nicht verfügbar Standardrückgabe");
        return new ArrayList<>();
    }
}
