package de.hska.iwi.microservice.catalog.web;

import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Credential;
import de.hska.iwi.microservice.catalog.entity.Product;
import de.hska.iwi.microservice.catalog.service.ICatalogServiceFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */

@RestController
public class CatalogController implements ICatalogController {
    private static final Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    private ICatalogServiceFacade serviceFacade;

    @Override
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<List<Category>>(serviceFacade.getAllCategories(), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResponseEntity<Category> createCategory(@RequestHeader("usr") String username, @RequestHeader("pass") String password, @RequestBody Category category) {
        return new ResponseEntity<Category>(serviceFacade.createCategory(new Credential(username, password), category), HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@RequestHeader("usr") String username, @RequestHeader("pass") String password, @PathVariable(value = "categoryId") int categoryId, @RequestBody Category category) {
        return new ResponseEntity<Category>(serviceFacade.updateCategory(new Credential(username, password), categoryId, category), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategory(@PathVariable(value = "categoryId") int categoryId) {
        return new ResponseEntity<Category>(serviceFacade.getCategory(categoryId), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCategory(@RequestHeader("usr") String username, @RequestHeader("pass") String password, @PathVariable(value = "categoryId") int categoryId) {
        return new ResponseEntity<Boolean>(serviceFacade.deleteCategory(new Credential(username, password), categoryId), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestHeader("usr") String username, @RequestHeader("pass") String password, @RequestBody Product product) {
        return new ResponseEntity<Product>(serviceFacade.createProduct(new Credential(username, password), product), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestHeader("usr") String username, @RequestHeader("pass") String password, @PathVariable(value = "productId") int productId, @RequestBody Product product) {
        return new ResponseEntity<Product>(serviceFacade.updateProduct(new Credential(username, password), productId, product), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteProduct(@RequestHeader("usr") String username, @RequestHeader("pass") String password, @PathVariable(value = "productId") int productId) {
        return new ResponseEntity<Boolean>(serviceFacade.deleteProduct(productId), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name = "categoryId", required = false, defaultValue = "-1") int categoryId) {
        return new ResponseEntity<List<Product>>(serviceFacade.getProducts(categoryId), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<Product>(serviceFacade.getProduct(id), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/catalog/", method = RequestMethod.GET)
    public ResponseEntity<List<Catalog>> getCatalog() {
        return new ResponseEntity<List<Catalog>>(serviceFacade.getCatalog(), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/catalog/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<List<Catalog>> getCatalogCategorie(@PathVariable(value = "categoryId") int categoryId) {
        return new ResponseEntity<List<Catalog>>(serviceFacade.getCatalogCategorie(categoryId), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Catalog>> searchCatalog(@RequestParam("minPrice") float minPrice,@RequestParam("maxPrice") float maxPrice,@RequestParam("content") String content) {
        return new ResponseEntity<List<Catalog>>(serviceFacade.searchCatalog(minPrice, maxPrice, content), HttpStatus.OK);
    }
}
