package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.CategoryService;
import de.hska.iwi.microservice.catalog.entity.Category;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public class CategoryServiceClient implements CategoryService{
    private static final Logger logger = Logger.getLogger(CategoryServiceClient.class);

    private final CategoryService service;

    public CategoryServiceClient(String categoryServiceUrl) {
        this.service = Feign.builder()
                .contract(new JAXRSContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(CategoryService.class, categoryServiceUrl);
    }

    public List<Category> getAllCategories() {
        logger.info("Liefere alle Kategorien zurück vom Kategorie-Service.");
        return service.getAllCategories();
    }

    public Category createCategory(Category category) {
        logger.info("Erzeuge eine neue Kategorie beim Kategorie-Service");
        return service.createCategory(category);
    }


    public Category updateCategory(Category category) {
        logger.info("Aktuallisiere eine Kategorie beim Kategorie-Service");
        return service.updateCategory(category);
    }


    public Category getCategory(int categoryId) {
        logger.info("Liefere eine spezielle Kategorie zurück vom Kategorie-Service.");
        return service.getCategory(categoryId);
    }

    public boolean deleteCategory(int categoryId) {
        logger.info("Lösche eine Kategorie beim Kategorie-Service");
        return service.deleteCategory(categoryId);
    }
}
