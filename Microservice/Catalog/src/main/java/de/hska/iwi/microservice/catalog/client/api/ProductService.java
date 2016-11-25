package de.hska.iwi.microservice.catalog.client.api;

import de.hska.iwi.microservice.catalog.entity.Product;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    Product updateProduct(int id, Product product);

    /**
     * Löscht ein vorhandenes Produkt aus dem System.
     */
    boolean deleteProduct(int id);

    /**
     * Liefert alle Produktecon dem System zurück oder wennn der Parameter übergeben wird, werden alle Produkte zurückgeliefert, welche die entsprechende CategorieId besitzen
     */
    List<Product> getProducts(int categorieId);

    /**
     * Liefert ein spezielles Produkt zurück.
     */
    Product getProduct(int id);
}
