package com.hackathon.favoritepayee.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_accounts")
public class FavoriteAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many accounts belong to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(nullable = false, length = 20)
    private String iban;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getIban() {
        return iban;
    }

    public String getBankName() {
        return bankName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}