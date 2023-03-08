package com.example.ecombackend.controller;

import com.example.ecombackend.Entity.PartDetails;
import com.example.ecombackend.Service.PartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PartControllerTest {

    @Mock
    private PartService partService;

    @InjectMocks
    private PartController partController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCarsByModelId() {
        String carModelId = "123";
        List<PartDetails> expectedCars = new ArrayList<>();
        expectedCars.add(new PartDetails());
        expectedCars.add(new PartDetails());
        when(partService.getCarsByModelId(carModelId)).thenReturn(expectedCars);

        List<PartDetails> actualCars = partController.getCarsByModelId(carModelId);

        assertEquals(expectedCars, actualCars);
    }

    @Test
    void testPlaceOrder() {
        Map<String, Long> orderDetails = new HashMap<>();
        orderDetails.put("partId", 1L);
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Order placed successfully!");
        //when(partService.placeOrder(orderDetails)).thenReturn(true);

        ResponseEntity<String> actualResponse = partController.placeOrder(orderDetails);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testUpdateParts() {
        Map<String, Long> orderDetails = new HashMap<>();
        orderDetails.put("partId", 1L);
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Stock Added");
        //when(partService.updateParts(orderDetails)).thenReturn(true);

        ResponseEntity<String> actualResponse = partController.updateParts(orderDetails);

        assertEquals(expectedResponse, actualResponse);
    }
}
