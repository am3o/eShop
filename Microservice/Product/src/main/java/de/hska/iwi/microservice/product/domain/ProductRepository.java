package de.hska.iwi.microservice.product.domain;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product save(Product product);

    Product findById(long id);

    Product findByName(String name);

    Product findByPrice(double price);

    Product findByCategory(Category category);
}
