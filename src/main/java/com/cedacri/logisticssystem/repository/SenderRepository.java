package com.cedacri.logisticssystem.repository;

import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.model.Sender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderRepository extends CrudRepository<Sender, Long> {
}
