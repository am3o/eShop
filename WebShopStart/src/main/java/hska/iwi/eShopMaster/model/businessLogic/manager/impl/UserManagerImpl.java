package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

public class UserManagerImpl implements UserManager {

  private final static String BASIS_URL_AUTH = "http://localhost:8081/api/auth/";

  private final Logger logger = Logger.getLogger(UserManagerImpl.class);
  private final ObjectMapper parser = new ObjectMapper();

  @Override
  public boolean existUser(String username, String password) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_AUTH);

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .header("usr", username).header("pass", password)
          .get(ClientResponse.class);

      usr = parser.readValue(response.getEntity(String.class), User.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return !(usr instanceof User);
  }

  @Override
  public User checkPermission(String username, String password) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_AUTH)
          .queryParam("permission", "true");

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .header("usr", username).header("pass", password)
          .get(ClientResponse.class);

      usr = parser.readValue(response.getEntity(String.class), User.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return usr;
  }

  @Override
  public User createUser(String username, String name, String lastname, String password,
      String role) {
    User usr = new User(0, name, lastname, username, password, role);
    try {
      Client client = Client.create();
      WebResource webResource = client.resource(BASIS_URL_AUTH);

      ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE)
          .post(ClientResponse.class, parser.writeValueAsString(usr));

      usr = parser.readValue(response.getEntity(String.class), User.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return usr;
  }

  @Override
  public boolean deleteUser(int userId) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(BASIS_URL_AUTH)
          .path(String.valueOf(userId));

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .delete(ClientResponse.class);

      usr = parser.readValue(response.getEntity(String.class), User.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return !(usr instanceof User);
  }

  @Override
  public User loginUser(String username, String password) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client.resource(BASIS_URL_AUTH)
          .path("login");

      ClientResponse response = webResource.header("usr", username)
          .accept(MediaType.APPLICATION_JSON_TYPE)
          .header("pass", password).put(ClientResponse.class);

      usr = parser.readValue(response.getEntity(String.class), User.class);
      usr.setRole(this.checkPermission(usr.getUsername(), usr.getPassword()).getRole());
    } catch (Exception ex) {
      logger.error(ex);
    }
    return usr;
  }

  @Override
  public User logoutUser(String username, String password) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client.resource(BASIS_URL_AUTH)
          .path("logout");

      ClientResponse response = webResource.header("usr", username)
          .accept(MediaType.APPLICATION_JSON_TYPE)
          .header("pass", password).put(ClientResponse.class);

      usr = parser.readValue(response.getEntity(String.class), User.class);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return usr;
  }
}
