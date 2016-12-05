package de.hska.iwi.microservice.product.web;

import de.hska.iwi.microservice.product.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IProductController {
    /**
     * Erstellt ein neues Produkt in dem System.
     */
    ResponseEntity<Product> createProduct(Product product);

    /**
     * Aktualisiert ein vorhandenes Produkt im System.
     */
    ResponseEntity<Product> updateProduct(int productId, Product product);

    /**
     * Löscht ein vorhandenes Produkt aus dem System.
     */
    ResponseEntity<Boolean> deleteProduct(int productId);

    /**
     * Liefert alle Produktecon dem System zurück oder wennn der Parameter übergeben wird, werden alle Produkte zurückgeliefert, welche die entsprechende CategorieId besitzen
     */
    ResponseEntity<List<Product>> getProducts(int categorieId);

    /**
     * Liefert ein spezielles Produkt zurück.
     */
    ResponseEntity<Product> getProduct(int productId);

    /**
     * Sucht nach passenden Produkten anhand der Kritieren minPrice, maxPrice, name
     */
    ResponseEntity<List<Product>> search(String details, Double minPrice, Double maxPrice);
}
