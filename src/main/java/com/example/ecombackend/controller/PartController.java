package com.example.ecombackend.controller;

import com.example.ecombackend.Entity.PartDetails;
import com.example.ecombackend.Repository.PartRepository;
import com.example.ecombackend.Service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "")
public class PartController {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartService partService;

    @GetMapping("/shop/stock/model/{carModelId}")
    public List<PartDetails> getCarsByModelId(@PathVariable String carModelId) {
        return partService.getCarsByModelId(carModelId);
    }

    @PostMapping("/shop/order")
    public ResponseEntity<String> placeOrder(@RequestBody Map<String, Long> orderDetails) {
        try {
            partService.placeOrder(orderDetails);
            return ResponseEntity.ok().body("Order placed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateParts(@RequestBody Map<String, Long> orderDetails) {
        try {
            partService.updateParts(orderDetails);
            return ResponseEntity.ok().body("Stock Added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order");
        }
    }
}
