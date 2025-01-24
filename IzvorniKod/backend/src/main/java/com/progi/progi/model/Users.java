package com.progi.progi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "USERID", nullable = false)
    private Integer id;

    @Column(name = "USERNAME", length = 200)
    private String username;

    @Column(name = "EMAIL", length = 200)
    private String email;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    public Users() {
    }

    public Users(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}