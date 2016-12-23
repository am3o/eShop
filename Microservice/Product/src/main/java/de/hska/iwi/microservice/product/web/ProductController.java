package de.hska.iwi.microservice.product.web;

import de.hska.iwi.microservice.product.entity.Product;
import de.hska.iwi.microservice.product.service.IProductServiceFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController implements IProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class);

    private static final double SEARCH_MIN_PRICE_DEFAULT = Double.MIN_VALUE;
    private static final double SEARCH_MAX_PRICE_DEFAULT = Double.MAX_VALUE;

    private final IProductServiceFacade productServiceFacade;

    @Autowired
    public ProductController(IProductServiceFacade productServiceFacade) {
        super();
        logger.info("Erzeuge Produkt-Steuereinheit");
        this.productServiceFacade = productServiceFacade;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.info("Erzeuge neues Produkt");
        return new ResponseEntity<Product>(productServiceFacade.createProduct(product), HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") int productId, @RequestBody Product product) {
        logger.info("Aktualisere vorhandenes Produkt");
        product.setId(productId);
        return new ResponseEntity<Product>(productServiceFacade.updateProduct(product), HttpStatus.ACCEPTED);
    }

    @Override
    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") int productId) {
        logger.info("Lösche vorhandenes Produkt");
        return new ResponseEntity<Boolean>(productServiceFacade.deleteProduct(productId), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "categoryId", required = false, defaultValue = "-1") int categorieId) {
        List<Product> result;
        if(categorieId != -1) {
            logger.info(String.format("Liefere alle Produkte der Kategorie %d zurück.", categorieId));
            result = productServiceFacade.getProductsByCategoryId(categorieId);
        }else {
            logger.info("Liefere alle Produkte zurück");
            result = productServiceFacade.getProducts();
        }
        return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {
        logger.info("Liefere spezielles Produkt zurück");
        return new ResponseEntity<Product>(productServiceFacade.getProduct(productId), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> search(@RequestParam(value = "details", required = false, defaultValue = "") String details,
                                                @RequestParam(value = "minPrice", required = false) Double minPrice,
                                                @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        double searchMinPrice = minPrice instanceof Double ? minPrice : SEARCH_MIN_PRICE_DEFAULT;
        double searchMaxPrice = maxPrice instanceof Double ? maxPrice : SEARCH_MAX_PRICE_DEFAULT;
        List<Product> result = productServiceFacade.search(details, minPrice, maxPrice);
        return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
    }
}
