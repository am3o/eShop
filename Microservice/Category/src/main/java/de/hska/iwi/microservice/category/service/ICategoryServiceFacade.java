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

import de.hska.iwi.microservice.category.entity.Category;

import java.util.List;

/**
 * Created by ameo on 27.05.16.
 */
public interface ICategoryServiceFacade {
    /**
     * Liefert alle Kategorien zurück, welche im System vorhanden sind.
     */
    List<Category> getAllCategories();

    /**
     * Erstellt eine neue Kategorie im System
     */
    Category createCategory(Category category);

    /**
     * Aktualisiert eine bestehende Kategorie
     */
    Category updateCategory(Category category);

    /**
     * Liefert eine spezielle Kategorie zurück.
     */
    Category getCategory(int categoryId);

    /**
     * Löscht eine vorhandene Kategorie aus dem System.
     */
    boolean deleteCategory(int categoryId);
}
