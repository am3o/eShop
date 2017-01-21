package hska.iwi.eShopMaster.model.businessLogic.manager.entity;

/**
 * Created by ameo on 19.01.17.
 */
public class Category {

  private int id;
  private String name = "undefined";

  public Category() {
    super();
  }

  public Category(String name) {
    this();
    this.name = name;
  }

  public Category(int id, String name) {
    this(name);
    this.id = id;
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

  @Override
  public String toString() {
    return String.format("Category[id=%d, name=%s]", this.id, this.name);
  }
}
