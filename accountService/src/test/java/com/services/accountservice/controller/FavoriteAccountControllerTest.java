package com.services.accountservice.controller;

import com.services.accountservice.contoller.FavoriteAccountController;
import com.services.accountservice.dto.FavoriteAccountRequest;
import com.services.accountservice.entity.FavoriteAccount;
import com.services.accountservice.service.FavoriteAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FavoriteAccountController.class)

class FavoriteAccountControllerTest {

    @Autowired

    private MockMvc mockMvc;

    @MockitoBean

    private FavoriteAccountService service;

    @Autowired

    private ObjectMapper objectMapper;


    @Test

    void testCreate() throws Exception {

        FavoriteAccountRequest request = new FavoriteAccountRequest();

        request.setName("Test");

        request.setIban("ES502134495444432222");

        FavoriteAccount response = new FavoriteAccount();

        response.setName("Test");

        when(service.create(eq("C1"), any(FavoriteAccountRequest.class)))

                .thenReturn(response);

        mockMvc.perform(post("/api/customers/C1/favorites")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("Test"));


    }


    @Test

    void testGetAll() throws Exception {

        FavoriteAccount acc = new FavoriteAccount();

        acc.setName("Test");

        when(service.getAll("C1", 0, 2))

                .thenReturn(new PageImpl<>(List.of(acc)));

        mockMvc.perform(get("/api/customers/C1/favorites")

                        .param("page", "0")

                        .param("size", "2"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.content[0].name").value("Test"));

    }


    @Test
    void testUpdate() throws Exception {

        FavoriteAccountRequest request = new FavoriteAccountRequest();
        request.setName("Updated");
        request.setIban("ES502134495444432222");

        FavoriteAccount response = new FavoriteAccount();
        response.setName("Updated");

        when(service.update(eq("C1"), eq(1L), any(FavoriteAccountRequest.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/customers/C1/favorites/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

}