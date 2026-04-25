package com.hackathon.favoritepayee.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // One customer can have many favorite accounts
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<FavoriteAccount> favoriteAccounts;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<FavoriteAccount> getFavoriteAccounts() {
        return favoriteAccounts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFavoriteAccounts(List<FavoriteAccount> favoriteAccounts) {
        this.favoriteAccounts = favoriteAccounts;
    }
}