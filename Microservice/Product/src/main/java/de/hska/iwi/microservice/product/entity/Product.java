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

package de.hska.iwi.microservice.product.entity;

/**
 * Created by ameo on 11.06.16.
 */
public class Product {

  private int id;

  private String name;

  private double price;

  private String details;

  private int categoryId;

  public Product() {
  }

  public Product(int id, String name, double price, String details, int categoryId) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.details = details;
    this.categoryId = categoryId;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public String toString() {
    return String.format("Product [id=%d, name=%s, details=%s, category=%s]", id, name, details,
        categoryId);
  }
}
