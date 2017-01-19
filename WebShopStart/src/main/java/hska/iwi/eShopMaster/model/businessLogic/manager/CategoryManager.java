package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Category;
import java.util.List;

public interface CategoryManager {

  List<Category> getCategories();

  Category getCategory(int id);

  void addCategory(String name);

  void delCategoryById(int id);
}
