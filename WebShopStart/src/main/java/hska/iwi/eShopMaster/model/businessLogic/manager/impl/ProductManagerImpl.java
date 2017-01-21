package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager {

  private final static String BASIS_URL_CATALOG = "http://localhost:8081/api/catalog/";
  private final static String BASIS_URL_PRODUCT = "http://localhost:8081/api/catalog/product/";

  private final Logger logger = Logger.getLogger(ProductManagerImpl.class);
  private final ObjectMapper parser = new ObjectMapper();

  private final User currentUser;

  public ProductManagerImpl(User currentUser) {
    this.currentUser = currentUser;
  }

  @Override
  public List<Product> getProducts() {
    List<Product> products = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_PRODUCT);

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .get(ClientResponse.class);

      products = parser.readValue(response.getEntity(String.class), List.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return products;
  }

  @Override
  public Product getProductById(int id) {
    Product product = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_PRODUCT)
          .path(String.valueOf(id));

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .get(ClientResponse.class);

      product = parser.readValue(response.getEntity(String.class), Product.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return product;
  }

  @Override
  public int addProduct(String name, double price, int categoryId, String details) {
    Product product = new Product(name, price, details, categoryId);
    try {
      Client client = Client.create();
      WebResource webResource = client.resource(BASIS_URL_PRODUCT);

      ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE)
          .accept(MediaType.APPLICATION_JSON_TYPE)
          .type(MediaType.APPLICATION_JSON_TYPE)
          .header("usr", currentUser.getUsername())
          .header("pass", currentUser.getPassword())
          .post(ClientResponse.class, parser.writeValueAsString(product));

      product = parser.readValue(response.getEntity(String.class), Product.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return product.getId();
  }

  @Override
  public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice,
      Double searchMaxPrice) {
    List<Product> products = null;

    if (!(searchMinPrice instanceof Double)) {
      searchMinPrice = Double.MIN_VALUE;
    }

    if (!(searchMaxPrice instanceof Double)) {
      searchMaxPrice = Double.MAX_VALUE;
    }

    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_CATALOG)
          .path("search")
          .queryParam("details", searchValue)
          .queryParam("minPrice", String.valueOf(searchMinPrice))
          .queryParam("maxPrice", String.valueOf(searchMaxPrice));

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .get(ClientResponse.class);

      products = parser.readValue(response.getEntity(String.class), List.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return products;
  }

  @Override
  public boolean deleteProductsByCategoryId(int categoryId) {
    //ToDo: Implementation
    return false;
  }

  @Override
  public void deleteProductById(int id) {
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_PRODUCT)
          .path(String.valueOf(id));

      webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .header("usr", currentUser.getUsername())
          .header("pass", currentUser.getPassword())
          .delete();
    } catch (Exception ex) {
      logger.error(ex);
    }
  }
}
