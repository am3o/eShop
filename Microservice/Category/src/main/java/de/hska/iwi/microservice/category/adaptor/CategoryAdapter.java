package de.hska.iwi.microservice.category.adaptor;

import de.hska.iwi.microservice.category.domian.CategoryDAO;
import de.hska.iwi.microservice.category.entity.Category;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Created by ameo on 03.01.17.
 */
public class CategoryAdapter {

  private static final Logger logger = Logger.getLogger(CategoryAdapter.class);

  public Category convertCategoryDAOToCategory(CategoryDAO categoryDAO) {
    if (categoryDAO instanceof CategoryDAO) {
      logger
          .info(String.format("Wandle Datenbank-Objekt in Kategorie: %s", categoryDAO.toString()));
      return new Category(categoryDAO.getId(), categoryDAO.getName());
    }
    return null;
  }

  public CategoryDAO convertCategoryToCategoryDAO(Category category) {
    if (category instanceof Category) {
      logger.info(String.format("Wandle Kategorie in Datenbank-Objekt: %s", category.toString()));
      return new CategoryDAO(category.getId(), category.getName());
    }
    return null;
  }

  public List<Category> convertCategoryDAOListToCategoryList(List<CategoryDAO> categoryDAOList) {
    if (categoryDAOList instanceof List) {
      logger.info("Wandle Datenbank-Objekt-Liste in Kategorie-Liste");
      List<Category> resultCategoryList = new ArrayList<>();
      for (CategoryDAO categoryDAO : categoryDAOList) {
        resultCategoryList.add(this.convertCategoryDAOToCategory(categoryDAO));
      }
      return resultCategoryList;
    }
    return null;
  }

}
