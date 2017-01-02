package de.hska.iwi.microservice.product.adaptor;

import de.hska.iwi.microservice.product.domain.ProductDAO;
import de.hska.iwi.microservice.product.entity.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameo on 02.01.17.
 */
public class ProductAdapter {

  public Product convertProductDAOToProduct(ProductDAO productDAO) {
    if (productDAO instanceof ProductDAO) {
      return new Product(productDAO.getId(), productDAO.getName(), productDAO.getPrice(),
          productDAO.getDetails(), productDAO.getCategoryId());
    }
    return null;
  }

  public ProductDAO convertProductToProductDAO(Product product) {
    if (product instanceof Product) {
      return new ProductDAO(product.getId(), product.getName(), product.getPrice(),
          product.getDetails(), product.getCategoryId());
    }
    return null;
  }

  public List<Product> convertListProductDAOToListProduct(List<ProductDAO> listProductDAO) {
    if (listProductDAO instanceof List) {
      List<Product> resultList = new ArrayList<>();
      for (ProductDAO productDAO : listProductDAO) {
        resultList.add(this.convertProductDAOToProduct(productDAO));
      }
    }
    return null;
  }
}
