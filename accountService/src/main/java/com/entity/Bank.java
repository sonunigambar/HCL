package com.hackathon.favoritepayee.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @Column(length = 10)
    private String code;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    public String getCode() {
        return code;
    }

    public String getBankName() {
        return bankName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}