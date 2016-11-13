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

    Category updateCategory(Category category);

    Category getCategory(int categoryId);

    boolean deleteCategory(int categoryId);

    Product createProduct(Product product);

    Product updateProduct(int id, Product product);

    boolean deleteProduct(int id);

    List<Product> getProducts();

    Product getProduct(int id);

    List<Product> getProductsByCategorieId(int id);

    List<Catalog> getCatalog();

    List<Catalog> getCatalogCategorie(int categoryId);

    List<Catalog> searchCatalog(float minPrice, float maxPrice, String content);
}
