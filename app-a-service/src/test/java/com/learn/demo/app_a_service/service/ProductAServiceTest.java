package com.learn.demo.app_a_service.service;

import com.learn.demo.app_a_service.repository.ProductARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class ProductAServiceTest {
    @Autowired
    private WebClient webClient;
    @Autowired
    private ProductARepository repository;

    //todo ---
    @BeforeEach
    void setUp() {
    }

    @Test
    void test1() {
    }

    @Test
    void saveBilling() {
    }

    @Test
    void findAll() {
    }

    @org.junit.jupiter.api.Test
    void findById() {
    }

    @org.junit.jupiter.api.Test
    void getResponseFallbackRetry() {
    }

    @org.junit.jupiter.api.Test
    void getResponseFallbackRateLimiter() {
    }

    @org.junit.jupiter.api.Test
    void getResponseFallbackCircuitBreaker() {
    }
}