package com.cedacri.logisticssystem.repository;

import com.cedacri.logisticssystem.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
