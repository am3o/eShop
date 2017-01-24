package de.hska.iwi.microservice.oauth.entity;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by ameo on 21.01.17.
 */
public class CustomerCredential implements UserDetails {

  private long id;
  private String username;
  private String password;
  private String role;

  public CustomerCredential() {
    super();
  }

  public CustomerCredential(final long id, final String username, final String password,
      final String role) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public long getId() {
    return id;
  }

  public String getRole() {
    return role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(String.format("ROLE_%s", this.role)));
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("UserModelDetails{");
    sb.append("id=").append(id);
    sb.append(", username='").append(username).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", role='").append(role).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
