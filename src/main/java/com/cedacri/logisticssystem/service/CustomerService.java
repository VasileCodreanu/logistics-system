package com.cedacri.logisticssystem.service;

import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.repository.ReceiverRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final ReceiverRepository repository;

    public CustomerService(ReceiverRepository repository) {
        this.repository = repository;
    }

    public Customer save(Customer entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Customer> getAll() {
        List<Customer> brokerList = new ArrayList<>();
        Iterable<Customer> iterable = repository.findAll();
        iterable.forEach(brokerList::add);

        return brokerList;
    }

    public Customer getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No Customer with ID="+id));
    }

    public Customer update(Customer entity) {
        Customer foundCustomer = this.getById(entity.getID());
        return repository.save(entity);
    }

    public void delete(Customer entity) {
        repository.delete(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
