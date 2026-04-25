package com.services.accountservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BankCode {

    @Id
    private String code;

    private String bankName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}