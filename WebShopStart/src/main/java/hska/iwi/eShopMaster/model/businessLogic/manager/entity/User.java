package hska.iwi.eShopMaster.model.businessLogic.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ameo on 18.01.17.
 */
public class User implements java.io.Serializable {

  private static final long serialVersionUID = -983183915002226000L;

  private long id;
  private String name;
  private String lastname;
  private String username;
  private String password;
  private String role = "User";

  public User() {
    super();
  }

  public User(long id, String name, String lastname, String username, String password,
      String role) {
    super();
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @JsonIgnore
  public boolean isEmpty() {
    return !(this.name instanceof String && this.lastname instanceof String
        && this.lastname instanceof String && this.password instanceof String);
  }

  @Override
  public String toString() {
    return String
        .format("User[id=%d, name=%s, lastname=%s, username=%s, password=%s, role=%d]", id,
            name, lastname, username, password, role);
  }
}
