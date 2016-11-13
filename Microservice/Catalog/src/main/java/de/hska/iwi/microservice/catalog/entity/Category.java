package de.hska.iwi.microservice.catalog.entity;

/**
 * Created by ameo on 13.11.16.
 */
public class Category {
    private int id;
    private String name;

    public Category() {
        super();
    }

    public Category(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Category[id=%d, name=%s]", this.id, this.name);
    }
}
