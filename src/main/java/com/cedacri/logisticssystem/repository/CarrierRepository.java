package com.cedacri.logisticssystem.repository;

import com.cedacri.logisticssystem.model.Carrier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends CrudRepository<Carrier, Long> {

}
