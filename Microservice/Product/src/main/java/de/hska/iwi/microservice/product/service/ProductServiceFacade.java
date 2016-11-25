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

import de.hska.iwi.microservice.product.domain.ProductDAO;
import de.hska.iwi.microservice.product.domain.ProductRepository;
import de.hska.iwi.microservice.product.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameo on 11.06.16.
 */
@Service
public class ProductServiceFacade implements IProductServiceFacade {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceFacade(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    private ProductDAO convertToProductDAO(Product product) {
        return new ProductDAO(product.getId(), product.getName(), product.getPrice(), product.getDetails(), product.getCategoryId());
    }

    private List<Product> convertToListProduct(List<ProductDAO> list) {
        List<Product> result = new ArrayList<>();
        for (ProductDAO productDAO : list)
            result.add(productDAO.toProduct());
        return result;
    }

    @Override
    public Product createProduct(Product product) {
        ProductDAO productDAO = this.convertToProductDAO(product);
        return productRepository.save(productDAO).toProduct();
    }

    @Override
    public Product updateProduct(Product product) {
        ProductDAO productDAO = this.convertToProductDAO(product);
        return productRepository.save(productDAO).toProduct();
    }

    @Override
    public boolean deleteProduct(int id) {
        ProductDAO obj = productRepository.findById(id);
        if(obj instanceof ProductDAO) {
            productRepository.delete(obj);
        }
        return !(productRepository.findById(id) instanceof ProductDAO);
    }

    @Override
    public List<Product> getProducts() {
        List<ProductDAO> result = productRepository.findAll();
        return this.convertToListProduct(result);
    }

    @Override
    public List<Product> getProductsByCategoryId(int id) {
        List<ProductDAO> result = productRepository.findByCategoryId(id);
        return this.convertToListProduct(result);
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findById(id).toProduct();
    }
}
