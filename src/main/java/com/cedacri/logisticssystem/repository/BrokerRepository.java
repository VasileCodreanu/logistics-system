package com.cedacri.logisticssystem.repository;

import com.cedacri.logisticssystem.model.Broker;
import com.cedacri.logisticssystem.service.BrokerService;
import org.springframework.data.repository.CrudRepository;

public interface BrokerRepository extends CrudRepository<Broker, Long> {
}
