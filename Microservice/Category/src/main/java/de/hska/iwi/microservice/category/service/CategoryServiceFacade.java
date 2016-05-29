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

import de.hska.iwi.microservice.category.domian.Category;
import de.hska.iwi.microservice.category.domian.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ameo on 27.05.16.
 */
@Service
public class CategoryServiceFacade implements ICategoryServiceFacade {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceFacade(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public Category getCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean deleteCategory(int id) {
        categoryRepository.delete((long) id);
        return categoryRepository.exists((long) id);
    }
}
