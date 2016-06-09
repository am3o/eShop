package de.hska.iwi.microservice.product.web;

import de.hska.iwi.microservice.product.domain.Product;


public interface IProductController {

    Product createProduct();

    Product updateProduct(int id, Product product);

    Product deleteProduct(int id);

    Product getProduct(int id);

}
