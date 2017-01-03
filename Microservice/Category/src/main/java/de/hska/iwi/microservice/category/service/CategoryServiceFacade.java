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

import de.hska.iwi.microservice.category.adaptor.CategoryAdapter;
import de.hska.iwi.microservice.category.domian.CategoryDAO;
import de.hska.iwi.microservice.category.domian.CategoryRepository;
import de.hska.iwi.microservice.category.entity.Category;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ameo on 27.05.16.
 */
@Service
public class CategoryServiceFacade implements ICategoryServiceFacade {

  private final Logger logger = Logger.getLogger(CategoryServiceFacade.class);

  private final CategoryRepository categoryRepository;
  private final CategoryAdapter categoryAdapter = new CategoryAdapter();

  @Autowired
  public CategoryServiceFacade(CategoryRepository categoryRepository) {
    super();
    this.categoryRepository = categoryRepository;
  }

  private Category convertToCategory(CategoryDAO categoryDAO) {
    logger.info("Wandle Datenbank-Objekt in Kategorie");
    return this.categoryAdapter.convertCategoryDAOToCategory(categoryDAO);
  }

  private CategoryDAO convertToCategoryDAO(Category category) {
    logger.info("Wandle Kategorie in Datenbank-Objekt");
    return this.categoryAdapter.convertCategoryToCategoryDAO(category);
  }

  private List<Category> convertToListCategory(List<CategoryDAO> list) {
    logger.info("Wandle Datenbank-Objekt Liste in Kategorie-Liste");
    return this.categoryAdapter.convertCategoryDAOListToCategoryList(list);
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
    return this.convertToCategory(categoryRepository.save(categoryDAO));
  }

  @Override
  public Category updateCategory(Category category) {
    logger
        .info(String.format("Aktualisiere vorhande Kategory mit dem Wert:%s", category.toString()));
    CategoryDAO categoryDAO = this.convertToCategoryDAO(category);
    return this.convertToCategory(categoryRepository.save(categoryDAO));
  }

  @Override
  public Category getCategory(int categoryId) {
    logger.info(String.format("Liefere Kategorie zurück mit dem Wert: %d", categoryId));
    return this.convertToCategory(categoryRepository.findById(categoryId));
  }

  @Override
  public boolean deleteCategory(int categoryId) {
    logger.info(String.format("Lösche Kategorie mit dem Wert: %d", categoryId));
    CategoryDAO obj = categoryRepository.findById(categoryId);
    if (obj instanceof CategoryDAO) {
      logger.info(
          String.format("Lösche einen Eintrag von Produkt aus der Datenbank: %s", obj.toString()));
      categoryRepository.delete(obj);
    }
    return !(categoryRepository.findById(categoryId) instanceof CategoryDAO);
  }
}
