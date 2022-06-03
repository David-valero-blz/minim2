package com.example.myapplication.models;



public class User {
    private String id;
    private String name;
    private String username;
    private String mail;
    private String password;

    public User(String name, String username, String mail, String password) {
        this.name = name;
        this.username=username;
        this.mail=mail;
        this.password=password;
        this.id=id;

    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {return id;}

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPass(String password) {
        this.password = password;
    }
}
