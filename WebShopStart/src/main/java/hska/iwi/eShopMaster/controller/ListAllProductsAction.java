package hska.iwi.eShopMaster.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import java.util.List;
import java.util.Map;

public class ListAllProductsAction extends ActionSupport {

  /**
   *
   */
  private static final long serialVersionUID = -94109228677381902L;

  User user;
  private List<Product> products;

  public String execute() throws Exception {
    String result = "input";

    Map<String, Object> session = ActionContext.getContext().getSession();
    user = (User) session.get("webshop_user");

    if (user instanceof User) {
      ProductManager productManager = new ProductManagerImpl(user);
      this.products = productManager.getProducts();
      result = "success";
    }

    return result;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

}
