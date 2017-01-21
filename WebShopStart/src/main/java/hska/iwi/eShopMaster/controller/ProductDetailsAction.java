package hska.iwi.eShopMaster.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import java.util.Map;

public class ProductDetailsAction extends ActionSupport {

  private User user;
  private int id;
  private Product product;

  /**
   *
   */
  private static final long serialVersionUID = 7708747680872125699L;

  public String execute() throws Exception {

    String res = "input";

    Map<String, Object> session = ActionContext.getContext().getSession();
    user = (User) session.get("webshop_user");

    if (user instanceof User) {
      ProductManager productManager = new ProductManagerImpl(user);
      product = productManager.getProductById(id);

      CategoryManagerImpl categoryManager = new CategoryManagerImpl(user);
      product.setCategory(categoryManager.getCategory(product.getCategoryId()));

      res = "success";
    }

    return res;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
