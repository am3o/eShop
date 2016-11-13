package de.hska.iwi.microservice.catalog.entity;

/**
 * Created by ameo on 13.11.16.
 */
public class Catalog {
    private final Category category;
    private final Product product;

    public Catalog(Category category, Product product) {
        this.category = category;
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public Product getProduct() {
        return product;
    }
}
