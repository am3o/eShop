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

package de.hska.iwi.microservice.category.web;

import de.hska.iwi.microservice.category.entity.Category;
import de.hska.iwi.microservice.category.service.ICategoryServiceFacade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ameo on 27.05.16.
 */
@RestController
public class CategoryController implements ICategoryController {
    private final Logger logger = Logger.getLogger(CategoryController.class);

    private ICategoryServiceFacade categoryServiceFacade;

    @Autowired
    public CategoryController(ICategoryServiceFacade categoryServiceFacade) {
        logger.info("Erzeuge Kategorie-Steuereinheit");
        this.categoryServiceFacade = categoryServiceFacade;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("Service-Aufruf: getAllCategories");
        return new ResponseEntity<List<Category>>(categoryServiceFacade.getAllCategories(), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        logger.info(String.format("Service-Aufruf: createCategory mit dem Wert: %s", category.toString()));
        return new ResponseEntity<Category>(categoryServiceFacade.createCategory(category), HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        logger.info(String.format("Service-Aufruf: updateCategory mit dem Wert: %s", category.toString()));
        return new ResponseEntity<Category>(categoryServiceFacade.updateCategory(category), HttpStatus.ACCEPTED);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategory(@PathVariable("id") int categoryId) {
        logger.info(String.format("Service-Aufruf: getCategory mit dem Wert: %s", categoryId));
        return new ResponseEntity<Category>(categoryServiceFacade.getCategory(categoryId), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int categoryId) {
        logger.info(String.format("Service-Aufruf: deleteCategory mit dem Wert: %s", categoryId));
        return new ResponseEntity<Boolean>(categoryServiceFacade.deleteCategory(categoryId), HttpStatus.ACCEPTED);
    }
}
