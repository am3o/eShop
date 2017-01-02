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

import de.hska.iwi.microservice.product.adaptor.ProductAdapter;
import de.hska.iwi.microservice.product.domain.ProductDAO;
import de.hska.iwi.microservice.product.domain.ProductRepository;
import de.hska.iwi.microservice.product.entity.Product;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ameo on 11.06.16.
 */
@Service
public class ProductServiceFacade implements IProductServiceFacade {

  private static final Logger logger = Logger.getLogger(ProductServiceFacade.class);

  private final ProductRepository productRepository;
  private final ProductAdapter productAdapter;

  @Autowired
  public ProductServiceFacade(ProductRepository productRepository) {
    super();
    logger.info("Erzeuge Product-Service");
    this.productRepository = productRepository;
    this.productAdapter = new ProductAdapter();
  }

  private ProductDAO convertToProductDAO(Product product) {
    logger.info("Wandle Product in Datenbank-Objekt");
    return this.productAdapter.convertProductToProductDAO(product);
  }

  private Product convertToProduct(ProductDAO productDAO) {
    logger.info("Wandle Datenbank-Objekt in Product: %s");
    return this.productAdapter.convertProductDAOToProduct(productDAO);
  }

  private List<Product> convertToListProduct(List<ProductDAO> productDAOList) {
    logger.info("Wandle Datenbank-Objekt Liste in Product-Liste");
    return this.productAdapter.convertListProductDAOToListProduct(productDAOList);
  }

  @Override
  public Product createProduct(Product product) {
    logger.info(String.format("Erzeuge neuen Eintrag in der Datenbank: %s", product.toString()));
    ProductDAO resultProduct = this.convertToProductDAO(product);
    return this.convertToProduct(productRepository.save(resultProduct));
  }

  @Override
  public Product updateProduct(Product product) {
    logger
        .info(String.format("Aktualisiere einen Eintrag in der Datenbank: %s", product.toString()));
    ProductDAO resultProduct = this.convertToProductDAO(product);
    return this.convertToProduct(productRepository.save(resultProduct));
  }

  @Override
  public boolean deleteProduct(int id) {
    ProductDAO obj = productRepository.findById(id);
    if (obj instanceof ProductDAO) {
      logger.info(
          String.format("Lösche einen Eintrag von Produkt aus der Datenbank: %s", obj.toString()));
      productRepository.delete(obj);
    }
    return !(productRepository.findById(id) instanceof ProductDAO);
  }

  @Override
  public List<Product> getProducts() {
    logger.info("Liefere alle Einträge von Produkten zurück.");
    List<ProductDAO> resultListProduct = productRepository.findAll();
    return this.convertToListProduct(resultListProduct);
  }

  @Override
  public List<Product> getProductsByCategoryId(int id) {
    logger
        .info(String.format("Liefere spezielles Produkt mit passender KategorieID:%d zurück", id));
    List<ProductDAO> resultListProduct = productRepository.findByCategoryId(id);
    return this.convertToListProduct(resultListProduct);
  }

  @Override
  public Product getProduct(int id) {
    logger.info(String.format("Liefere spezielles Produkt mit Id:%d zurück", id));
    ProductDAO resultProduct = productRepository.findById(id);
    return this.convertToProduct(resultProduct);
  }

  @Override
  public List<Product> search(String details, Double minPrice, Double maxPrice) {
    logger.info(String.format(
        "Liefere alle Produkte zurück mit passenden Kriterien [Details:%s; MinPrice:%s, MaxPrice:%s]",
        details, minPrice.toString(), maxPrice.toString()));
    List<ProductDAO> resultList = productRepository.search(details, minPrice, maxPrice);
    return this.convertToListProduct(resultList);
  }
}
