package de.hska.iwi.microservice.product.web;

import de.hska.iwi.microservice.product.entity.Product;

import java.util.List;


public interface IProductController {
    /**
     * Erstellt ein neues Produkt in dem System.
     */
    Product createProduct(Product product);

    /**
     * Aktualisiert ein vorhandenes Produkt im System.
     */
    Product updateProduct(int id, Product product);

    /**
     * Löscht ein vorhandenes Produkt aus dem System.
     */
    boolean deleteProduct(int id);

    /**
     * Liefert alle Produktecon dem System zurück.
     */
    List<Product> getProducts();

    /**
     * Liefert ein spezielles Produkt zurück.
     */
    Product getProduct(int id);

    /**
     * Liefert Produkte zurück mit einer gemeinsamen Categorie.
     */
    List<Product> getProductsByCategorieId(int id);
}
