package com.example.ecombackend.Repository;

import com.example.ecombackend.Entity.PartDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin("http://localhost:4200")
public interface PartRepository extends CrudRepository<PartDetails, String> {

    @Query("SELECT p FROM PartDetails p WHERE p.carModelId = :carModelId")
    List<PartDetails> findByCarModelId(String carModelId);

    @Query("SELECT p FROM PartDetails p WHERE p.componentId = :componentId")
    Optional<PartDetails> findByComponentId(String componentId);

}
