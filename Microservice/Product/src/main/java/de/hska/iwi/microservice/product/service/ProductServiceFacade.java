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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ameo on 11.06.16.
 */
@Service
public class ProductServiceFacade implements IProductServiceFacade {

  private final ProductRepository productRepository;
  private final ProductAdapter productAdapter;

  @Autowired
  public ProductServiceFacade(ProductRepository productRepository) {
    super();
    this.productRepository = productRepository;
    this.productAdapter = new ProductAdapter();
  }

  private ProductDAO convertToProductDAO(Product product) {
    return this.productAdapter.convertProductToProductDAO(product);
  }

  private Product convertToProduct(ProductDAO productDAO) {
    return this.productAdapter.convertProductDAOToProduct(productDAO);
  }

  private List<Product> convertToListProduct(List<ProductDAO> productDAOList) {
    return this.productAdapter.convertListProductDAOToListProduct(productDAOList);
  }

  @Override
  public Product createProduct(Product product) {
    ProductDAO resultProduct = this.convertToProductDAO(product);
    return this.convertToProduct(productRepository.save(resultProduct));
  }

  @Override
  public Product updateProduct(Product product) {
    ProductDAO resultProduct = this.convertToProductDAO(product);
    return this.convertToProduct(productRepository.save(resultProduct));
  }

  @Override
  public boolean deleteProduct(int id) {
    ProductDAO obj = productRepository.findById(id);
    if (obj instanceof ProductDAO) {
      productRepository.delete(obj);
    }
    return !(productRepository.findById(id) instanceof ProductDAO);
  }

  @Override
  public List<Product> getProducts() {
    List<ProductDAO> resultListProduct = productRepository.findAll();
    return this.convertToListProduct(resultListProduct);
  }

  @Override
  public List<Product> getProductsByCategoryId(int id) {
    List<ProductDAO> resultListProduct = productRepository.findByCategoryId(id);
    return this.convertToListProduct(resultListProduct);
  }

  @Override
  public Product getProduct(int id) {
    ProductDAO resultProduct = productRepository.findById(id);
    return this.convertToProduct(resultProduct);
  }

  @Override
  public List<Product> search(String details, double minPrice, double maxPrice) {
    List<ProductDAO> resultList = productRepository.search(details, minPrice, maxPrice);
    return this.convertToListProduct(resultList);
  }
}
