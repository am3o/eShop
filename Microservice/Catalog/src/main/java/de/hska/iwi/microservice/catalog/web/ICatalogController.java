package de.hska.iwi.microservice.catalog.web;

import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Product;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public interface ICatalogController {
    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category updateCategory(int categoryId, Category category);

    Category getCategory(int categoryId);

    boolean deleteCategory(int categoryId);

    Product createProduct(Product product);

    Product updateProduct(int productId, Product product);

    boolean deleteProduct(int productId);

    List<Product> getProducts(int categoryId);

    Product getProduct(int productId);

    List<Catalog> getCatalog();

    List<Catalog> getCatalogCategorie(int categoryId);

    List<Catalog> searchCatalog(float minPrice, float maxPrice, String content);
}
