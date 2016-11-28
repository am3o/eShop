package de.hska.iwi.microservice.catalog.web;

import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Credential;
import de.hska.iwi.microservice.catalog.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public interface ICatalogController {
    ResponseEntity<List<Category>> getAllCategories();

    ResponseEntity<Category> createCategory(String username, String password, Category category);

    ResponseEntity<Category> updateCategory(String username, String password, int categoryId, Category category);

    ResponseEntity<Category> getCategory(int categoryId);

    ResponseEntity<Boolean> deleteCategory(String username, String password, int categoryId);

    ResponseEntity<Product> createProduct(String username, String password, Product product);

    ResponseEntity<Product> updateProduct(String username, String password, int productId, Product product);

    ResponseEntity<Boolean> deleteProduct(String username, String password, int productId);

    ResponseEntity<List<Product>> getProducts(int categoryId);

    ResponseEntity<Product> getProduct(int productId);

    ResponseEntity<List<Catalog>> getCatalog();

    ResponseEntity<List<Catalog>> getCatalogCategorie(int categoryId);

    ResponseEntity<List<Catalog>> searchCatalog(float minPrice, float maxPrice, String content);
}
