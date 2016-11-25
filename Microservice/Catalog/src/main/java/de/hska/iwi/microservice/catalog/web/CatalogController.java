package de.hska.iwi.microservice.catalog.web;

import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Product;
import de.hska.iwi.microservice.catalog.service.CatalogServiceFacade;
import de.hska.iwi.microservice.catalog.service.ICatalogServiceFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Category> getAllCategories() {
        return serviceFacade.getAllCategories();
    }

    @Override
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category category) {
        return serviceFacade.createCategory(category);
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
    public Category updateCategory(@PathVariable(value = "categoryId") int categoryId, @RequestBody Category category) {
        return serviceFacade.updateCategory(categoryId, category);
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable(value = "categoryId") int categoryId) {
        return serviceFacade.getCategory(categoryId);
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
    public boolean deleteCategory(@PathVariable(value = "categoryId") int categoryId) {
        return serviceFacade.deleteCategory(categoryId);
    }

    @Override
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return serviceFacade.createProduct(product);
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable(value = "productId") int productId, @RequestBody Product product) {
        return serviceFacade.updateProduct(productId, product);
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
    public boolean deleteProduct(@PathVariable(value = "productId") int productId) {
        return serviceFacade.deleteProduct(productId);
    }

    @Override
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<Product> getProducts(@RequestParam(name = "categoryId", required = false, defaultValue = "-1") int categoryId) {
        return serviceFacade.getProducts(categoryId);
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable(value = "productId") int id) {
        return serviceFacade.getProduct(id);
    }

    @Override
    @RequestMapping(value = "/catalog/", method = RequestMethod.GET)
    public List<Catalog> getCatalog() {
        return serviceFacade.getCatalog();
    }

    @Override
    @RequestMapping(value = "/catalog/{categoryId}", method = RequestMethod.GET)
    public List<Catalog> getCatalogCategorie(@PathVariable(value = "categoryId") int categoryId) {
        return serviceFacade.getCatalogCategorie(categoryId);
    }

    @Override
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Catalog> searchCatalog(@RequestParam("minPrice") float minPrice,@RequestParam("maxPrice") float maxPrice,@RequestParam("content") String content) {
        return serviceFacade.searchCatalog(minPrice, maxPrice, content);
    }
}
