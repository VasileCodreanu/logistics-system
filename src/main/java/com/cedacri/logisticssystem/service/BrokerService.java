package com.cedacri.logisticssystem.service;

import com.cedacri.logisticssystem.model.Broker;
import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.repository.BrokerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrokerService {

    private final BrokerRepository repository;

    public BrokerService(BrokerRepository repository) {
        this.repository = repository;
    }

    public Broker save(Broker entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Broker> getAll() {
        List<Broker> brokerList = new ArrayList<>();
        Iterable<Broker> iterable = repository.findAll();
        iterable.forEach(brokerList::add);

        return brokerList;
    }
    public Broker getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No Broker with ID="+id));
    }

    public Broker update(Broker entity) {
        Broker foundBroker = this.getById(entity.getID());
        return repository.save(foundBroker);
    }

    public void delete(Broker entity) {
        repository.delete(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
