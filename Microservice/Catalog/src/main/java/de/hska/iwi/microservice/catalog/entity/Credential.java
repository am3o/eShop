package de.hska.iwi.microservice.catalog.entity;

/**
 * Created by ameo on 25.11.16.
 */
public class Credential {
    private final String usr;
    private final String pass;

    public Credential(String username, String password) {
        this.usr = username;
        this.pass = password;
    }

    public String getUsername() {
        return usr;
    }

    public String getPassword() {
        return pass;
    }
}
