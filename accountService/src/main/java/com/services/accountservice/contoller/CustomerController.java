package com.services.accountservice.contoller;

import com.services.accountservice.entity.Customer;
import com.services.accountservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/createCustomer")
    public ResponseEntity<?> create(@Validated @RequestBody Customer request) {

        return ResponseEntity.ok(service.create(request));
    }
}