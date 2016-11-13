package de.hska.iwi.microservice.catalog.web;

import de.hska.iwi.microservice.catalog.entity.Catalog;
import de.hska.iwi.microservice.catalog.entity.Category;
import de.hska.iwi.microservice.catalog.entity.Product;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ameo on 13.11.16.
 */

@RestController
public class CatalogController implements ICatalogController {
    private static final Logger logger = Logger.getLogger(CatalogController.class);

    @Override
    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    @RequestMapping(value = "/category/", method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category category) {
        return null;
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
    public Category updateCategory(@RequestBody Category category) {
        return null;
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable(value = "categoryId") int categoryId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
    public boolean deleteCategory(@PathVariable(value = "categoryId") int categoryId) {
        return false;
    }

    @Override
    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return null;
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable(value = "productId") int id, @RequestBody Product product) {
        return null;
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
    public boolean deleteProduct(@PathVariable(value = "productId") int id) {
        return false;
    }

    @Override
    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return null;
    }

    @Override
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable(value = "productId") int id) {
        return null;
    }

    @Override
    @RequestMapping(value = "/product/category/{categoryId}", method = RequestMethod.GET)
    public List<Product> getProductsByCategorieId(@PathVariable(value = "categoryId") int id) {
        return null;
    }

    @Override
    @RequestMapping(value = "/catalog/", method = RequestMethod.GET)
    public List<Catalog> getCatalog() {
        return null;
    }

    @Override
    @RequestMapping(value = "/catalog/{categoryId}", method = RequestMethod.GET)
    public List<Catalog> getCatalogCategorie(@PathVariable(value = "categoryId") int categoryId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Catalog> searchCatalog(@RequestParam("minPrice") float minPrice,@RequestParam("maxPrice") float maxPrice,@RequestParam("content") String content) {
        return null;
    }
}
