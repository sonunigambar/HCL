package com.services.accountservice.service;

import com.services.accountservice.dto.FavoriteAccountRequest;
import com.services.accountservice.entity.BankCode;
import com.services.accountservice.entity.FavoriteAccount;
import com.services.accountservice.exception.ResourceNotFoundException;
import com.services.accountservice.repositary.BankCodeRepository;
import com.services.accountservice.repositary.CustomerRepository;
import com.services.accountservice.repositary.FavoriteAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteAccountServiceTest {

    @Mock

    private FavoriteAccountRepository favoriteRepo;

    @Mock

    private BankCodeRepository bankRepo;

    @Mock

    private CustomerRepository customerRepo;

    @InjectMocks

    private FavoriteAccountService service;


    @Test
    void testCreate_success() {

        String customerId = "C1";

        FavoriteAccountRequest request = new FavoriteAccountRequest();

        request.setName("Test");

        request.setIban("ES502134495444432222");

        when(customerRepo.existsById(customerId)).thenReturn(true);

        BankCode bankCode = new BankCode();

        bankCode.setBankName("HDFC");

        when(bankRepo.findById("2134")).thenReturn(Optional.of(bankCode));

        when(favoriteRepo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        FavoriteAccount result = service.create(customerId, request);

        assertNotNull(result);

        assertEquals("Test", result.getName());

        assertEquals("HDFC", result.getBankName());

        verify(favoriteRepo, times(1)).save(any());

    }

    @Test

    void testCreate_customerNotFound() {

        when(customerRepo.existsById("C1")).thenReturn(false);

        FavoriteAccountRequest request = new FavoriteAccountRequest();

        assertThrows(ResourceNotFoundException.class,

                () -> service.create("C1", request));

    }

    @Test

    void testCreate_invalidBank() {

        String customerId = "C1";

        FavoriteAccountRequest request = new FavoriteAccountRequest();

        request.setIban("ES502134495444432222");

        when(customerRepo.existsById(customerId)).thenReturn(true);

        when(bankRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,

                () -> service.create(customerId, request));

    }

    @Test

    void testGetById_success() {

        FavoriteAccount acc = new FavoriteAccount();

        when(favoriteRepo.findByIdAndCustomerId(1L, "C1"))

                .thenReturn(Optional.of(acc));

        FavoriteAccount result = service.getById("C1", 1L);

        assertNotNull(result);

    }

    @Test
    void testGetById_notFound() {

        when(favoriteRepo.findByIdAndCustomerId(1L, "C1"))

                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,

                () -> service.getById("C1", 1L));

    }

    @Test

    void testUpdate_success() {

        FavoriteAccount existing = new FavoriteAccount();

        FavoriteAccountRequest request = new FavoriteAccountRequest();

        request.setName("Updated");

        request.setIban("ES502134495444432222");

        when(favoriteRepo.findByIdAndCustomerId(1L, "C1"))

                .thenReturn(Optional.of(existing));

        BankCode bankCode = new BankCode();

        bankCode.setBankName("ICICI");

        when(bankRepo.findById("2134")).thenReturn(Optional.of(bankCode));

        when(favoriteRepo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        FavoriteAccount result = service.update("C1", 1L, request);

        assertEquals("Updated", result.getName());

        assertEquals("ICICI", result.getBankName());

    }


    @Test
    void testExtractBankCode_invalidIban() {

        FavoriteAccountRequest request = new FavoriteAccountRequest();

        request.setIban("SHORT");

        when(customerRepo.existsById("C1")).thenReturn(true);

        assertThrows(ResourceNotFoundException.class,

                () -> service.create("C1", request));

    }

    @Test
    void testGetAll_success() {

        String customerId = "C1";

        FavoriteAccount acc1 = new FavoriteAccount();

        FavoriteAccount acc2 = new FavoriteAccount();

        Page<FavoriteAccount> mockPage =

                new PageImpl<>(List.of(acc1, acc2));

        when(favoriteRepo.findByCustomerId(eq(customerId), any(Pageable.class)))

                .thenReturn(mockPage);

        Page<FavoriteAccount> result = service.getAll(customerId, 0, 2);

        assertNotNull(result);

        assertEquals(2, result.getContent().size());

        verify(favoriteRepo, times(1))

                .findByCustomerId(eq(customerId), any(Pageable.class));

    }

}