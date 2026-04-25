package com.services.accountservice.repositary;

import com.services.accountservice.entity.BankCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCodeRepository extends JpaRepository<BankCode, String> {
}
