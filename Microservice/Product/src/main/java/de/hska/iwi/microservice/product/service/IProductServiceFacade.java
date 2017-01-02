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

package de.hska.iwi.microservice.product.service;

import de.hska.iwi.microservice.product.entity.Product;
import java.util.List;

/**
 * Created by ameo on 11.06.16.
 */
public interface IProductServiceFacade {

  /**
   * Erstellt ein neues Produkt in dem System.
   */
  Product createProduct(Product product);

  /**
   * Aktualisiert ein vorhandenes Produkt im System.
   */
  Product updateProduct(Product product);

  /**
   * Löscht ein vorhandenes Produkt aus dem System.
   */
  boolean deleteProduct(int id);

  /**
   * Liefert alle Produkte aus dem System zurück.
   */
  List<Product> getProducts();

  /**
   * Liefert alle Produkte mit der speziellen Kategorie aus dem System zurück.
   */
  List<Product> getProductsByCategoryId(int id);

  /**
   * Liefert ein spezielles Produkt zurück.
   */
  Product getProduct(int id);

  /**
   * Sucht nach passenden Produkten anhand der gegebenen Kriterien name, minPrice, maxPrice.
   */
  List<Product> search(String details, Double minPrice, Double maxPrice);
}
