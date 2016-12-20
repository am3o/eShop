package de.hska.iwi.microservice.catalog.client;

import de.hska.iwi.microservice.catalog.client.api.CategoryService;
import de.hska.iwi.microservice.catalog.entity.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */
public class CategoryServiceClient implements CategoryService{
    private static final Logger logger = Logger.getLogger(CategoryServiceClient.class);

    private final String serviceUrl;

    @Autowired
    private RestTemplate restClient;

    public CategoryServiceClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public List<Category> getAllCategories() {
        return restClient.getForObject(String.format("%s/", serviceUrl), List.class);
    }

    @Override
    public Category createCategory(Category category) {
        return restClient.postForEntity(String.format("%s/", serviceUrl), category, Category.class).getBody();
    }

    @Override
    public Category updateCategory(Category category) {
        restClient.put(String.format("%s/%d", serviceUrl, category.getId()), category);
        return this.getCategory(category.getId());
    }

    @Override
    public Category getCategory(int categoryId) {
        return restClient.getForObject(String.format("%s/%d", serviceUrl, categoryId), Category.class);
    }

    @Override
    public Boolean deleteCategory(int categoryId) {
        restClient.delete(String.format("%s/%d", serviceUrl, categoryId));
        return !(this.getCategory(categoryId) instanceof Category);
    }
}
