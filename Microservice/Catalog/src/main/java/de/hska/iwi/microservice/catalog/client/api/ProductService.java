package de.hska.iwi.microservice.catalog.client.api;

import de.hska.iwi.microservice.catalog.entity.Product;
import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public interface ProductService {
    /**
     * Erstellt ein neues Produkt in dem System.
     */
    Product createProduct(Product product);

    /**
     * Aktualisiert ein vorhandenes Produkt im System.
     */
    Product updateProduct(int productId, Product product);

    /**
     * Löscht ein vorhandenes Produkt aus dem System.
     */
    Boolean deleteProduct(int productId);

    /**
     * Liefert alle Produktecon dem System zurück oder wennn der Parameter übergeben wird, werden alle Produkte zurückgeliefert, welche die entsprechende CategorieId besitzen
     */
    List<Product> getProducts(int categorieId);

    /**
     * Liefert ein spezielles Produkt zurück.
     */
    Product getProduct(int productId);

    /**
     * Liefert anhand der gegebenen Kriterien eine Menge an Produkten zurück.
     */
    List<Product> search(String details, double minPrice, double maxPrice);
}
