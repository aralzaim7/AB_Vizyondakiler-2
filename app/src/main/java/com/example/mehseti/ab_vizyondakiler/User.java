package com.example.mehseti.ab_vizyondakiler;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    public String email;
    public String password;
    boolean isAuthenticated;
    public  boolean isReserved;

    public User(int id,String email,String password,boolean isReserved)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isReserved = isReserved;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
