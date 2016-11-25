package de.hska.iwi.microservice.catalog.client.api;

import de.hska.iwi.microservice.catalog.entity.Category;
import feign.Headers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public interface CategoryService {
    /**
     * Liefert alle Kategorien zurück.
     *
     * @return Liste von Kategorien
     */
    List<Category> getAllCategories();

    /**
     * Erstellt eine neue Kategorie
     *
     * @param category Kategorie
     * @return Kategorie
     */
    Category createCategory(Category category);

    /**
     * Aktualisiert eine bestehende Kategorie
     *
     * @param category Kategorie
     * @return Kategorie
     */
    Category updateCategory(Category category);

    /**
     * Liefert eine spezielle Kategorie zurück.
     *
     * @return Kategorie
     */
    Category getCategory(int categoryId);

    /**
     * Löscht eine vorhandene Kategorie.
     */
    boolean deleteCategory(int categoryId);
}
