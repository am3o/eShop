package de.hska.iwi.microservice.catalog.client.api;

import de.hska.iwi.microservice.catalog.entity.Category;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public interface CategoryService {
    /**
     * Liefert alle Kategorien zurück.
     */
    List<Category> getAllCategories();

    /**
     * Erstellt eine neue Kategorie
     */
    Category createCategory(Category category);

    /**
     * Aktualisiert eine bestehende Kategorie
     */
    Category updateCategory(Category category);

    /**
     * Liefert eine spezielle Kategorie zurück.e
     */
    Category getCategory(int categoryId);

    /**
     * Löscht eine vorhandene Kategorie.
     */
    Boolean deleteCategory(int categoryId);
}
