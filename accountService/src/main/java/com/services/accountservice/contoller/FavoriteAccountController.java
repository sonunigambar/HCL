package com.services.accountservice.contoller;

import com.services.accountservice.dto.FavoriteAccountRequest;
import com.services.accountservice.entity.FavoriteAccount;
import com.services.accountservice.service.FavoriteAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteAccountController {

    @Autowired
    private FavoriteAccountService service;

    // CREATE
    @PostMapping("/customers/{customerId}/favorites")
    public ResponseEntity<?> create(
            @PathVariable String customerId,
            @Validated @RequestBody FavoriteAccountRequest request) {

        return ResponseEntity.ok(service.create(customerId, request));
    }

    @GetMapping("/customers/{customerId}/favorites")
    public ResponseEntity<Page<FavoriteAccount>> getAll(
            @PathVariable String customerId,
            @RequestParam int page,
            @RequestParam int size) {

        return ResponseEntity.ok(service.getAll(customerId, page, size));
    }


    @PutMapping("/customers/{customerId}/favorites/{id}")
    public ResponseEntity<?> update(
            @PathVariable String customerId,
            @PathVariable Long id,
            @Validated @RequestBody FavoriteAccountRequest request) {

        return ResponseEntity.ok(service.update(customerId, id, request));
    }
}
