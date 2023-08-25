package com.cedacri.logisticssystem.repository;

import com.cedacri.logisticssystem.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ReceiverRepository extends CrudRepository<Customer, Long> {
}
