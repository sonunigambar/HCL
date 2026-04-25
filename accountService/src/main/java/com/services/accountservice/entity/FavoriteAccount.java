package com.services.accountservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "favorite_account")
public class FavoriteAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    private Instant createdAt;
    private Instant updatedAt;

    @Column(name = "customer_id")
    private String customerId;
    private String name;

    @NotBlank(message = "IBAN is mandatory")

    @Size(max = 20, message = "IBAN max length is 20")

    @Pattern(
            regexp = "^[A-Za-z0-9]+$",

            message = "IBAN must be alphanumeric"
    )
    private String iban;
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}