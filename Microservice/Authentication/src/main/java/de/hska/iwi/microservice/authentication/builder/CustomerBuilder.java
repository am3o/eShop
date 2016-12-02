package de.hska.iwi.microservice.authentication.builder;

import de.hska.iwi.microservice.authentication.entity.Customer;

/**
 * Created by ameo on 02.12.16.
 */
public class CustomerBuilder {
    private String username;
    private String password;
    private Customer.Permission role = Customer.Permission.User;

    public CustomerBuilder() {
        super();
    }

    public CustomerBuilder(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomerBuilder setRole(Customer.Permission role) {
        this.role = role;
        return this;
    }

    public Customer build() {
        return new Customer(Long.MIN_VALUE, "", "", username, password, role);
    }

    public Customer buildEmpty() {
        return new Customer(Long.MIN_VALUE, "", "", "", "", null);
    }
}
