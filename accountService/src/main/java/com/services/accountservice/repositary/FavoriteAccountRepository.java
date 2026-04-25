package com.services.accountservice.repositary;


import com.services.accountservice.entity.FavoriteAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteAccountRepository extends JpaRepository<FavoriteAccount, Long> {
    long countByCustomerId(String customerId);

    Page<FavoriteAccount> findByCustomerId(String customerId, Pageable pageable);

    Optional<FavoriteAccount> findByIdAndCustomerId(Long id, String customerId);
//    Page<FavoriteAccount> findAll(Pageable pageable);
}
