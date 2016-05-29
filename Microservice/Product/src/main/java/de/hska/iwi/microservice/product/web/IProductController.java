package de.hska.iwi.microservice.product.web;

import de.hska.iwi.microservice.product.domain.Product;


public interface IProductController {

    Product createProduct();

    Product updateProduct(long id, Product product);

    Product deleteProduct(long id);

    Product getProduct(long id);

}
