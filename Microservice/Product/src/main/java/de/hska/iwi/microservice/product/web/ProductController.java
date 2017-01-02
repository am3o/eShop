package de.hska.iwi.microservice.product.web;

import de.hska.iwi.microservice.product.entity.Product;
import de.hska.iwi.microservice.product.service.IProductServiceFacade;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
    logger.info(String.format("Erzeuge neues Produkt %s", product.toString()));
    return new ResponseEntity<Product>(productServiceFacade.createProduct(product),
        HttpStatus.CREATED);
  }

  @Override
  @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
  public ResponseEntity<Product> updateProduct(@PathVariable("productId") int productId,
      @RequestBody Product product) {
    product.setId(productId);
    logger.info(String.format("Aktualisere vorhandenes Produkt %s", product.toString()));
    return new ResponseEntity<Product>(productServiceFacade.updateProduct(product),
        HttpStatus.ACCEPTED);
  }

  @Override
  @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") int productId) {
    logger.info(String.format("Lösche vorhandenes Produkt mit ID: %d", productId));
    return new ResponseEntity<Boolean>(productServiceFacade.deleteProduct(productId),
        HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<List<Product>> getProducts(
      @RequestParam(value = "categoryId", required = false, defaultValue = "-1") int categorieId) {
    List<Product> result;
    if (categorieId != -1) {
      logger.info(String.format("Liefere alle Produkte der Kategorie %d zurück.", categorieId));
      result = productServiceFacade.getProductsByCategoryId(categorieId);
    } else {
      logger.info("Liefere alle Produkte zurück");
      result = productServiceFacade.getProducts();
    }
    if (result instanceof List) {
      logger.info(String.format("Anzahl an gefunden Produkte: %d", result.size()));
    }
    return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
  }

  @Override
  @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
  public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {
    logger.info(String.format("Liefere spezielles Produkt mit ID: %d zurück", productId));
    return new ResponseEntity<Product>(productServiceFacade.getProduct(productId), HttpStatus.OK);
  }

  @Override
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public ResponseEntity<List<Product>> search(
      @RequestParam(value = "details", required = false, defaultValue = "") String details,
      @RequestParam(value = "minPrice", required = false) Double minPrice,
      @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
    Double searchMinPrice = minPrice instanceof Double ? minPrice : SEARCH_MIN_PRICE_DEFAULT;
    Double searchMaxPrice = maxPrice instanceof Double ? maxPrice : SEARCH_MAX_PRICE_DEFAULT;
    logger.info(String.format(
        "Suche nach passenden Produkten mit folgenden Parametern: [Details: %s; MinPrice: %s; MaxPrice: %s]",
        details, searchMinPrice.toString(), searchMaxPrice.toString()));
    List<Product> result = productServiceFacade.search(details, searchMinPrice, searchMaxPrice);
    if (result instanceof List) {
      logger.info(String.format("Anzahl an gefundenen Produkten: %d", result.size()));
    }
    return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
  }
}
