package hska.iwi.eShopMaster.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.entity.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import java.util.List;
import java.util.Map;

public class DeleteCategoryAction extends ActionSupport {

  /**
   *
   */
  private static final long serialVersionUID = 1254575994729199914L;

  private int catId;
  private List<Category> categories;

  public String execute() throws Exception {

    String res = "input";

    Map<String, Object> session = ActionContext.getContext().getSession();
    User user = (User) session.get("webshop_user");

    if (user instanceof User && user.getRole().equals("Admin")) {

      // Helper inserts new Category in DB:
      CategoryManager categoryManager = new CategoryManagerImpl(user);

      categoryManager.delCategoryById(catId);

      categories = categoryManager.getCategories();

      res = "success";

    }

    return res;

  }

  public int getCatId() {
    return catId;
  }

  public void setCatId(int catId) {
    this.catId = catId;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
