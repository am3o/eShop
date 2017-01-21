package hska.iwi.eShopMaster.model.businessLogic.manager.entity;

/**
 * Created by ameo on 19.01.17.
 */
public class Product {

  private int id;

  private String name;

  private double price;

  private String details;

  private String categoryName = "olaf";

  private int categoryId;

  private Category category = new Category();

  public Product() {
    super();
  }

  public Product(String name, double price, String details, int categoryId) {
    super();
    this.name = name;
    this.price = price;
    this.details = details;
    this.categoryId = categoryId;
  }

  public Product(int id, String name, double price, String details, int categoryId) {
    super();
    this.id = id;
    this.name = name;
    this.price = price;
    this.details = details;
    this.categoryId = categoryId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getCategoryName() {
    return category.getName();
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
