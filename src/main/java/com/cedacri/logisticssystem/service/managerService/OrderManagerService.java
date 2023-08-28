package com.cedacri.logisticssystem.service.managerService;

import com.cedacri.logisticssystem.exceptions.customExceptions.EntityNotFoundException;
import com.cedacri.logisticssystem.model.Order;
import com.cedacri.logisticssystem.repository.OrderRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderManagerService {

  private final OrderRepository repository;


  public Order save(Order entity) {
    return repository.save(entity);
  }

  @Transactional(readOnly = true)
  public List<Order> getAll() {
    if (repository.findAll().isEmpty()) {
      throw new RuntimeException("No Order To be Returned!");
    }
    return repository.findAll();
  }

  public Order getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Order with id=", id));
  }

  public Order update(Order entity) {
    this.getById(entity.getID());
    return repository.save(entity);
  }

  public void deleteById(Long id) {
    Order foundOrder = this.getById(id);
    repository.deleteById(foundOrder.getID());
  }
}
