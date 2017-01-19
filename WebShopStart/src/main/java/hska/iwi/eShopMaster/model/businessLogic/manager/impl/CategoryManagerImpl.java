package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Category;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

public class CategoryManagerImpl implements CategoryManager {

  private final static String BASIS_URL_CATEGORY = "http://localhost:8081/api/catalog/category/";

  private final Logger logger = Logger.getLogger(CategoryManagerImpl.class);
  private final ObjectMapper parser = new ObjectMapper();

  @Override
  public List<Category> getCategories() {
    List<Category> categories = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_CATEGORY);

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .get(ClientResponse.class);

      categories = parser.readValue(response.getEntity(String.class), List.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return categories;
  }

  @Override
  public Category getCategory(int id) {
    Category category = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(String.format("%s%d", BASIS_URL_CATEGORY, id));

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .get(ClientResponse.class);

      category = parser.readValue(response.getEntity(String.class), Category.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return category;
  }

  @Override
  public void addCategory(String name) {
    Category category = new Category(name);
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_CATEGORY);

      webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .post(ClientResponse.class, parser.writeValueAsString(category));
    } catch (Exception ex) {
      logger.error(ex);
    }
  }

  @Override
  public void delCategoryById(int id) {
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(String.format("%s%d", BASIS_URL_CATEGORY, id));

      webResource.accept(MediaType.APPLICATION_JSON_TYPE).delete();
    } catch (Exception ex) {
      logger.error(ex);
    }
  }
}
