package com.example.ecombackend.Service;

import com.example.ecombackend.Entity.PartDetails;
import com.example.ecombackend.Repository.PartRepository;
import com.example.ecombackend.exceptions.CarNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;


    public List<PartDetails> getCarsByModelId(String carModelId) {
        List<PartDetails> cars = partRepository.findByCarModelId(carModelId);
        if (cars.isEmpty()) {
            throw new CarNotFoundException("No cars found with model id : " + carModelId);
        }
        return cars;
    }

    @Transactional
    public void placeOrder(Map<String, Long> orderDetails) {
        for (Map.Entry<String, Long> entry : orderDetails.entrySet()) {
            String componentId = entry.getKey();
            Long unitInStock = entry.getValue();
            Optional<PartDetails> partStockOptional = partRepository.findByComponentId(componentId);
            PartDetails partStock = partStockOptional.orElseThrow(() -> new ResourceNotFoundException("Part stock not found with component ID: " + componentId));
            Long currentUnits = partStock.getUnitInStock();
            if (currentUnits < unitInStock) {
                throw new RuntimeException("Insufficient units in stock for component ID: " + componentId);
            }
            partStock.setUnitInStock(currentUnits - unitInStock);
            partRepository.save(partStock);
        }
    }

    @Transactional
    public void updateParts(Map<String, Long> orderDetails) {
        for (Map.Entry<String, Long> entry : orderDetails.entrySet()) {
            String componentId = entry.getKey();
            Long unitInStock = entry.getValue();
            Optional<PartDetails> partStockOptional = partRepository.findByComponentId(componentId);
            PartDetails partStock = partStockOptional.orElseThrow(() -> new ResourceNotFoundException("Part stock not found with component ID: " + componentId));
            Long currentUnits = partStock.getUnitInStock();
            if (currentUnits < unitInStock) {
                throw new RuntimeException("Insufficient units in stock for component ID: " + componentId);
            }
            partStock.setUnitInStock(currentUnits + unitInStock);
            partRepository.save(partStock);
        }
    }
}



