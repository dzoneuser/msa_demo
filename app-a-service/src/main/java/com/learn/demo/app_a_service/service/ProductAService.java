package com.learn.demo.app_a_service.service;

import com.learn.demo.app_a_service.dto.BillingRequest;
import com.learn.demo.app_a_service.exception.ResourceNotFoundException;
import com.learn.demo.app_a_service.repository.ProductARepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ProductAService {

    @Autowired
    private  WebClient webClient;
    @Autowired
    private ProductARepository repository;

    @Value("${productB.timeOutInSeconds}")
    private int timeOutInSeconds;


    public String test() {
        return webClient.get()
                .uri("productB/test/1")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(timeOutInSeconds))
                .block();
    }

    @Retry(name = "productBRetry" , fallbackMethod = "getResponseFallbackRetry")
    @RateLimiter(name = "productBRateLimiter", fallbackMethod =  "getResponseFallbackRateLimiter")
    @CircuitBreaker(name = "productBCircuitBreaker",  fallbackMethod =  "getResponseFallbackCircuitBreaker")
    public void saveBilling(BillingRequest billingRequest, String arg)
    throws ExecutionException, InterruptedException {
        CompletableFuture<String> response =  CompletableFuture.supplyAsync(() -> {
            return webClient.get()
                    .uri("productB/test/"+arg)
                    .retrieve()
                    .bodyToMono(String.class)
                //    .timeout(Duration.ofSeconds(timeOutInSeconds))
                    .block();

        });

        log.info("Response from product B : {}",response.get());

        billingRequest.setTimestamp(LocalDateTime.now());
        log.info("Saving request : {}",billingRequest);
        repository.save(billingRequest);
    }

    public List<BillingRequest> findAll(){
        return repository.findAll();
    }

    public BillingRequest findById(long id){
        return repository.findById( id).orElseThrow(() -> new ResourceNotFoundException(id,"id "+id+" not found"));
    }


    //fallback
    public void getResponseFallbackRetry(BillingRequest billingRequest, String arg, Exception throwable) {
        log.info("getResponseFallbackRetry {}",throwable.getMessage());
        throw new RuntimeException("getResponseFallbackRetry");
    }

    public void getResponseFallbackRateLimiter(BillingRequest billingRequest, String arg, Throwable throwable) {
       log.info("getResponseFallbackRateLimiter : {}",throwable.getMessage());
        //for demo
        throw new RuntimeException("getResponseFallbackRateLimiter");

    }

    public void getResponseFallbackCircuitBreaker(BillingRequest billingRequest, String arg, Throwable throwable) {
        log.info("getResponseFallbackCircuitBreaker {} ",throwable.getMessage());

        throw new RuntimeException("getResponseFallbackCircuitBreaker");

    }


    }
