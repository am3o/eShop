package hska.iwi.eShopMaster.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import java.util.List;
import java.util.Map;

public class AddCategoryAction extends ActionSupport {

  /**
   *
   */
  private static final long serialVersionUID = -6704600867133294378L;

  private String newCatName = null;

  private List<Category> categories;

  User user;

  public String execute() throws Exception {

    String res = "input";

    Map<String, Object> session = ActionContext.getContext().getSession();
    user = (User) session.get("webshop_user");
    if (user instanceof User && user.getRole().equals("Admin")) {
      CategoryManager categoryManager = new CategoryManagerImpl(user);

      // Add category
      categoryManager.addCategory(newCatName);

      // Go and get new Category list
      this.setCategories(categoryManager.getCategories());

      res = "success";
    }

    return res;

  }

  @Override
  public void validate() {
    if (getNewCatName().length() == 0) {
      addActionError(getText("error.catname.required"));
    }
    Map<String, Object> session = ActionContext.getContext().getSession();
    user = (User) session.get("webshop_user");

    // Go and get new Category list
    CategoryManager categoryManager = new CategoryManagerImpl(user);
    this.setCategories(categoryManager.getCategories());
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public String getNewCatName() {
    return newCatName;
  }

  public void setNewCatName(String newCatName) {
    this.newCatName = newCatName;
  }
}
