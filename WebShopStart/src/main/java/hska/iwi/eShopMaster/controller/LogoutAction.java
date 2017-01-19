package hska.iwi.eShopMaster.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.UserManagerImpl;
import java.util.Map;

public class LogoutAction extends ActionSupport {

  /**
   *
   */
  private static final long serialVersionUID = -530488065543708898L;
  private final String CONTEXT_USER = "webshop_user";

  public String execute() throws Exception {

    // Clear session:
    Map<String, Object> session = ActionContext.getContext().getSession();

    UserManager userManager = new UserManagerImpl();
    User currentUser = (User) session.get(CONTEXT_USER);

    userManager.logoutUser(currentUser.getUsername(), currentUser.getPassword());

    session.clear();

    return "success";
  }
}
