package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;


public interface UserManager {

  boolean existUser(String username, String password);

  User checkPermission(String username, String password);

  User createUser(String username, String name, String lastname, String password,
      String role);

  boolean deleteUser(int userId);

  User loginUser(String username, String password);

  User logoutUser(String username, String password);
}
