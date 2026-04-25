package com.services.accountservice.service;

import com.services.accountservice.dto.FavoriteAccountRequest;
import com.services.accountservice.entity.BankCode;
import com.services.accountservice.entity.FavoriteAccount;
import com.services.accountservice.exception.ResourceNotFoundException;
import com.services.accountservice.repositary.BankCodeRepository;
import com.services.accountservice.repositary.CustomerRepository;
import com.services.accountservice.repositary.FavoriteAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class FavoriteAccountService {

    @Autowired
    private FavoriteAccountRepository favoriteRepo;

    @Autowired

    private BankCodeRepository bankRepo;

    @Autowired

    private CustomerRepository customerRepo;

    public FavoriteAccount create(String customerId, FavoriteAccountRequest request) {

        if (!customerRepo.existsById(customerId)) {

            throw new ResourceNotFoundException("Customer not found");

        }
        String bankName = validateBank(request.getIban());


        FavoriteAccount acc = new FavoriteAccount();

        acc.setCustomerId(customerId);

        acc.setName(request.getName());

        acc.setIban(request.getIban());

        acc.setBankName(bankName);
        acc.setCreatedAt(Instant.now());

        return favoriteRepo.save(acc);

    }

    public Page<FavoriteAccount> getAll(String customerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return favoriteRepo.findByCustomerId(customerId, pageable);

    }

    public FavoriteAccount getById(String customerId, Long id) {

        return favoriteRepo.findByIdAndCustomerId(id, customerId)

                .orElseThrow(() -> new ResourceNotFoundException("Customer Not found"));

    }

    public FavoriteAccount update(String customerId, Long id, FavoriteAccountRequest request) {

        FavoriteAccount acc = getById(customerId, id);

        acc.setName(request.getName());

        acc.setIban(request.getIban());

        String bankName = validateBank(request.getIban());

        acc.setBankName(bankName);
        acc.setUpdatedAt(Instant.now());

        return favoriteRepo.save(acc);

    }
    public String validateBank(String iban) {

        String code = extractBankCode(iban);

        return bankRepo.findById(code)

                .map(BankCode::getBankName)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Bank"));
    }

    private String extractBankCode(String iban) {

        if (iban.length() < 20) {
            throw new ResourceNotFoundException("Invalid IBAN format");
        }
        return iban.substring(4, 8);

    }
}
