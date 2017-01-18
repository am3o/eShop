package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;


public interface UserManager {

  public boolean existUser(String username, String password);

  public User createUser(String username, String name, String lastname, String password,
      String role);

  public boolean deleteUser(int userId);

  public User loginUser(String username, String password);

  public User logoutUser(String username, String password);
}
