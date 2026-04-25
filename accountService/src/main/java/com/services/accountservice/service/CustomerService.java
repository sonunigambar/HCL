package com.services.accountservice.service;

import com.services.accountservice.entity.Customer;
import com.services.accountservice.repositary.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer create(Customer customer) {
        return repository.save(customer);
    }
}