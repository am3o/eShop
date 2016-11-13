package de.hska.iwi.microservice.catalog.service;

import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
@Service
public class CatalogServiceFacade implements ICatalogServiceFacade{
    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(int categoryId, Category category) {
        return null;
    }

    @Override
    public Category getCategory(int categoryId) {
        return null;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        return false;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategorieId(int id) {
        return null;
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
