package de.hska.iwi.microservice.product.adaptor;

import de.hska.iwi.microservice.product.domain.ProductDAO;
import de.hska.iwi.microservice.product.entity.Product;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Created by ameo on 02.01.17.
 */
public class ProductAdapter {

  private static final Logger logger = Logger.getLogger(ProductAdapter.class);

  public Product convertProductDAOToProduct(ProductDAO productDAO) {
    if (productDAO instanceof ProductDAO) {
      logger.info(String.format("Wandle Datenbank-Objekt in Produkt: %s", productDAO.toString()));
      return new Product(productDAO.getId(), productDAO.getName(), productDAO.getPrice(),
          productDAO.getDetails(), productDAO.getCategoryId());
    }
    return null;
  }

  public ProductDAO convertProductToProductDAO(Product product) {
    if (product instanceof Product) {
      logger.info(String.format("Wandle Produkt in Datenbank-Objekt: %s", product.toString()));
      return new ProductDAO(product.getId(), product.getName(), product.getPrice(),
          product.getDetails(), product.getCategoryId());
    }
    return null;
  }

  public List<Product> convertListProductDAOToListProduct(List<ProductDAO> listProductDAO) {
    if (listProductDAO instanceof List) {
      logger.info("Wandle Datenbank-Objekt-Liste in Produkt-Liste");
      List<Product> resultList = new ArrayList<>();
      for (ProductDAO productDAO : listProductDAO) {
        logger.info(productDAO.toString());
        resultList.add(this.convertProductDAOToProduct(productDAO));
      }
      return resultList;
    }
    return null;
  }
}
