package com.ygsoft.apps.oldpcbackup;

public class LoginParameters {

    private final String username, password;

    public LoginParameters(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }
}
