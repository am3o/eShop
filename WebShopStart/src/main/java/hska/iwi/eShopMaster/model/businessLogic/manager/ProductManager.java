package hska.iwi.eShopMaster.model.businessLogic.manager;


import hska.iwi.eShopMaster.model.businessLogic.manager.entity.Product;
import java.util.List;

public interface ProductManager {

  List<Product> getProducts();

  Product getProductById(int id);

  int addProduct(String name, double price, int categoryId, String details);

  List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice,
      Double searchMaxPrice);

  boolean deleteProductsByCategoryId(int categoryId);

  void deleteProductById(int id);
}
