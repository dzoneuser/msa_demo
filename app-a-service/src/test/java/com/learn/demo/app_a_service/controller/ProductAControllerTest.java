package com.learn.demo.app_a_service.controller;
import com.learn.demo.app_a_service.dto.BillingRequest;
import com.learn.demo.app_a_service.service.ProductAService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
class ProductAControllerTest {

    @InjectMocks
    private ProductAController productAController;

    @Mock

    private ProductAService productAService;

    @Test
    public void saveBillingTest() throws ExecutionException, InterruptedException {
        //todo need optmzn
        BillingRequest billingRequest = new BillingRequest();
        billingRequest.setTimestamp(LocalDateTime.now());;
        billingRequest.setBillingHeader("sample_header");;
        billingRequest.setBillingLines("sample_lines");;
        billingRequest.setBuyerParty("sample_party");;
        billingRequest.setSupplierParty("sample_supplier_party");

        verify(productAController, times(1)).saveBilling(billingRequest,"1");


    }

    @Test
    public void findByIdTest() throws Exception {

        BillingRequest billingRequest = new BillingRequest();
        billingRequest.setTimestamp(LocalDateTime.now());;
        billingRequest.setBillingHeader("sample_header");;
        billingRequest.setBillingLines("sample_lines");;
        billingRequest.setBuyerParty("sample_party");;
        billingRequest.setSupplierParty("sample_supplier_party");

        when(productAService.findById(Mockito.any(Long.class))).thenReturn(billingRequest);
        ResponseEntity<BillingRequest> result =  productAController.findById(1);
        assertThat(result.getStatusCode().value()).isEqualTo(200);
        Assertions.assertEquals(result.getBody().getBillingHeader(),billingRequest.getBillingHeader());

    }
    @Test
    public void findAllTest() throws Exception {

        BillingRequest billingRequest = new BillingRequest();
        billingRequest.setTimestamp(LocalDateTime.now());;
        billingRequest.setBillingHeader("sample_header");;
        billingRequest.setBillingLines("sample_lines");;
        billingRequest.setBuyerParty("sample_party");;
        billingRequest.setSupplierParty("sample_supplier_party");

        when(productAService.findAll()).thenReturn(List.of(billingRequest));
        ResponseEntity<List<BillingRequest>> result =  productAController.findAll();
        assertThat(result.getStatusCode().value()).isEqualTo(200);
        Assertions.assertEquals(result.getBody().get(0).getBillingHeader(),billingRequest.getBillingHeader());

    }

}