package com.example.ecombackend.Entity;

import jakarta.persistence.*;

@Entity
@Table
public class PartDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "car_model_id")
    private String carModelId;
    @Column
    private String componentName;
    @Column
    private String componentId;
    @Column(name = "unit_in_stock")
    private Long unitInStock;

    public PartDetails() {
    }

    public PartDetails(String carModelIds, String componentNames, String componentIds, Long unitInStocks) {
        carModelId = carModelIds;
        componentName = componentNames;
        componentId = componentIds;
        unitInStock = unitInStocks;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarModelId() {
        return carModelId;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getComponentId() {
        return componentId;
    }

    public Long getUnitInStock() {
        return unitInStock;
    }

    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public void setUnitInStock(Long unitInStock) {
        this.unitInStock = unitInStock;
    }

}