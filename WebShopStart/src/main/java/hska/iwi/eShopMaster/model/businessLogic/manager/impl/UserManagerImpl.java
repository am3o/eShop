package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import javax.ws.rs.core.MediaType;

public class UserManagerImpl implements UserManager {

  @Override
  public boolean existUser(String username, String password) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource("http://localhost:8081/api/auth/");

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .header("usr", username).header("pass", password)
          .get(ClientResponse.class);

      ObjectMapper objectMapper = new ObjectMapper();
      usr = objectMapper.readValue(response.getEntity(String.class), User.class);

    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    return !(usr instanceof User);
  }

  @Override
  public User createUser(String username, String name, String lastname, String password,
      String role) {
    User usr = new User(0, name, lastname, username, password, role);
    try {
      Client client = Client.create();
      WebResource webResource = client.resource("http://localhost:8081/api/auth/");

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .post(ClientResponse.class, usr);

      ObjectMapper objectMapper = new ObjectMapper();
      usr = objectMapper.readValue(response.getEntity(String.class), User.class);

    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    return usr;
  }

  @Override
  public boolean deleteUser(int userId) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client
          .resource(String.format("http://localhost:8081/api/auth/%d", userId));

      ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
          .delete(ClientResponse.class);

      ObjectMapper objectMapper = new ObjectMapper();
      usr = objectMapper.readValue(response.getEntity(String.class), User.class);

    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    return !(usr instanceof User);
  }

  @Override
  public User loginUser(String username, String password) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client.resource("http://localhost:8081/api/auth/login");

      ClientResponse response = webResource.header("usr", username)
          .accept(MediaType.APPLICATION_JSON_TYPE)
          .header("pass", password).put(ClientResponse.class);

      ObjectMapper objectMapper = new ObjectMapper();
      usr = objectMapper.readValue(response.getEntity(String.class), User.class);

    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    return usr;
  }

  @Override
  public User logoutUser(String username, String password) {
    User usr = null;
    try {
      Client client = Client.create();
      WebResource webResource = client.resource("http://localhost:8081/api/auth/logout");

      ClientResponse response = webResource.header("usr", username)
          .accept(MediaType.APPLICATION_JSON_TYPE)
          .header("pass", password).put(ClientResponse.class);

      ObjectMapper objectMapper = new ObjectMapper();
      usr = objectMapper.readValue(response.getEntity(String.class), User.class);

    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    return usr;
  }
}
