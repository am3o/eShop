package de.hska.iwi.microservice.product.web;

import de.hska.iwi.microservice.product.entity.Product;
import de.hska.iwi.microservice.product.service.IProductServiceFacade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController implements IProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class);

    private final IProductServiceFacade productServiceFacade;

    @Autowired
    public ProductController(IProductServiceFacade productServiceFacade) {
        super();
        logger.info("Erzeuge Produkt-Steuereinheit");
        this.productServiceFacade = productServiceFacade;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        logger.info("Erzeuge neues Produkt");
        return productServiceFacade.createProduct(product);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        logger.info("Aktualisere vorhandenes Produkt");
        product.setId(id);
        return productServiceFacade.updateProduct(product);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteProduct(@PathVariable("id") int id) {
        logger.info("Lösche vorhandenes Produkt");
        return productServiceFacade.deleteProduct(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> getProducts(@RequestParam(value = "categoryId", required = false, defaultValue = "-1") int categorieId) {
        List<Product> result;
        if(categorieId != -1) {
            logger.info(String.format("Liefere alle Produkte der Kategorie %d zurück.", categorieId));
            result = productServiceFacade.getProductsByCategoryId(categorieId);
        }else {
            logger.info("Liefere alle Produkte zurück");
            result = productServiceFacade.getProducts();
        }
        return result;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") int id) {
        logger.info("Liefere spezielles Produkt zurück");
        return productServiceFacade.getProduct(id);
    }
}
