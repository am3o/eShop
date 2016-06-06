/*
 *    Copyright (c) 2016.   Joshua Braun
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package de.hska.iwi.microservice.category.service;

import de.hska.iwi.microservice.category.domian.CategoryDAO;
import de.hska.iwi.microservice.category.domian.CategoryRepository;
import de.hska.iwi.microservice.category.entity.Category;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameo on 27.05.16.
 */
@Service
public class CategoryServiceFacade implements ICategoryServiceFacade {
    private final Logger logger = Logger.getLogger(CategoryServiceFacade.class);
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceFacade(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private CategoryDAO convertToCategoryDAO(Category category) {
        return new CategoryDAO(category.getId(), category.getName());
    }

    private List<Category> convertToListCategory(List<CategoryDAO> list) {
        List<Category> result = new ArrayList<>();
        for (CategoryDAO value : list) {
            result.add(value.toCategory());
        }
        return result;
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Liefere alle Kategorien zurück.");
        return this.convertToListCategory(categoryRepository.findAll());
    }

    @Override
    public Category createCategory(Category category) {
        logger.info(String.format("Erstelle neue Kategory mit dem Wert: %s", category.toString()));
        CategoryDAO categoryDAO = this.convertToCategoryDAO(category);
        return categoryRepository.save(categoryDAO).toCategory();
    }

    @Override
    public Category updateCategory(Category category) {
        logger.info(String.format("Aktualisiere vorhande Kategory mit dem Wert:%s", category.toString()));
        CategoryDAO categoryDAO = this.convertToCategoryDAO(category);
        return categoryRepository.save(categoryDAO).toCategory();
    }

    @Override
    public Category getCategory(int categoryId) {
        logger.info(String.format("Liefere Kategorie zurück mit dem Wert: %d", categoryId));
        return categoryRepository.findById(categoryId).toCategory();
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        //FIXME Abfrage ob Kategorie wirklich gelöscht wurde muss hinzugefügt werden
        logger.info(String.format("Lösche Kategorie mit dem Wert: %d", categoryId));
        categoryRepository.delete(categoryRepository.findById(categoryId));
        return true;
    }
}
