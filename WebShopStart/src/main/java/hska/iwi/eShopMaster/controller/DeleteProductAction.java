package hska.iwi.eShopMaster.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import java.util.Map;

public class DeleteProductAction extends ActionSupport {

  /**
   *
   */
  private static final long serialVersionUID = 3666796923937616729L;

  private int id;

  public String execute() throws Exception {

    String res = "input";

    Map<String, Object> session = ActionContext.getContext().getSession();
    User user = (User) session.get("webshop_user");

    if (user instanceof User && user.getRole().equals("Admin")) {

      ProductManager manager = new ProductManagerImpl(user);
      manager.deleteProductById(this.getId());

      res = "success";
    }

    return res;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
