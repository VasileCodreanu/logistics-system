package com.cedacri.logisticssystem.repository;

import com.cedacri.logisticssystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
