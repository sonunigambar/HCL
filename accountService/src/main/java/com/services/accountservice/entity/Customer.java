package com.services.accountservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.util.UUID;

@Entity
public class Customer {

    @Id
    private String customerId;

    private String name;

    @PrePersist
    public void generateId() {

        if (this.customerId == null) {

            this.customerId = UUID.randomUUID().toString();

        }

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
