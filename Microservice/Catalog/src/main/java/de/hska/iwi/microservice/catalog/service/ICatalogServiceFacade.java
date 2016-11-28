package de.hska.iwi.microservice.catalog.service;

import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Credential;
import de.hska.iwi.microservice.catalog.entity.Product;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public interface ICatalogServiceFacade {
    List<Category> getAllCategories();

    Category createCategory(Credential credential, Category category);

    Category updateCategory(Credential credential, int categoryId, Category category);

    Category getCategory(int categoryId);

    boolean deleteCategory(Credential credential, int categoryId);

    Product createProduct(Credential credential, Product product);

    Product updateProduct(Credential credential, int productId, Product product);

    boolean deleteProduct(int id);

    List<Product> getProducts(int categoryId);

    Product getProduct(int id);

    List<Catalog> getCatalog();

    List<Catalog> getCatalogCategorie(int categoryId);

    List<Catalog> searchCatalog(float minPrice, float maxPrice, String content);
}
