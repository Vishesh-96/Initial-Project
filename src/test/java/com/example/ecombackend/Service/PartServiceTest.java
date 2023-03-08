package com.example.ecombackend.Service;

import com.example.ecombackend.Entity.PartDetails;
import com.example.ecombackend.Repository.PartRepository;
import com.example.ecombackend.exceptions.CarNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PartServiceTest {

    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private PartService partService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCarsByModelIdTest() {
        List<PartDetails> expectedCars = new ArrayList<>();
        expectedCars.add(new PartDetails("Engine", "V8", "Audi", 10L));
        expectedCars.add(new PartDetails("Suspension", "Double wishbone", "Audi", 20L));

        when(partRepository.findByCarModelId("Audi")).thenReturn(expectedCars);

        List<PartDetails> actualCars = partService.getCarsByModelId("Audi");

        Assertions.assertEquals(expectedCars.size(), actualCars.size());
        Assertions.assertEquals(expectedCars.get(0).getComponentId(), actualCars.get(0).getComponentId());
        Assertions.assertEquals(expectedCars.get(1).getComponentId(), actualCars.get(1).getComponentId());

        verify(partRepository, times(1)).findByCarModelId(anyString());
    }

    @Test
    public void getCarsByModelIdNoCarsFoundTest() {
        when(partRepository.findByCarModelId("Audi")).thenReturn(Collections.emptyList());

        Assertions.assertThrows(CarNotFoundException.class, () -> {
            partService.getCarsByModelId("Audi");
        });

        verify(partRepository, times(1)).findByCarModelId(anyString());
    }

    @Test
    public void placeOrderTest() {
        Map<String, Long> orderDetails = new HashMap<>();
        orderDetails.put("Engine", 2L);
        orderDetails.put("Suspension", 3L);

        PartDetails engine = new PartDetails("Engine", "V8", "Audi", 10L);
        PartDetails suspension = new PartDetails("Suspension", "Double wishbone", "Audi", 20L);

        when(partRepository.findByComponentId("Engine")).thenReturn(Optional.of(engine));
        when(partRepository.findByComponentId("Suspension")).thenReturn(Optional.of(suspension));

        partService.placeOrder(orderDetails);

        Assertions.assertEquals(8L, engine.getUnitInStock());
        Assertions.assertEquals(17L, suspension.getUnitInStock());

        verify(partRepository, times(2)).findByComponentId(anyString());
        verify(partRepository, times(2)).save(any(PartDetails.class));
    }

    @Test
    public void placeOrderNotEnoughUnitsInStockTest() {
        Map<String, Long> orderDetails = new HashMap<>();
        orderDetails.put("Engine", 20L);

        PartDetails engine = new PartDetails("Engine", "V8", "Audi", 10L);

        when(partRepository.findByComponentId("Engine")).thenReturn(Optional.of(engine));

        Assertions.assertThrows(RuntimeException.class, () -> {
            partService.placeOrder(orderDetails);
        });

        Assertions.assertEquals(10L, engine.getUnitInStock());

    }
}
