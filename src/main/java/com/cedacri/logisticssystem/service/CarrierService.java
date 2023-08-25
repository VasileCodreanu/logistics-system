package com.cedacri.logisticssystem.service;

import com.cedacri.logisticssystem.model.Broker;
import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.repository.CarrierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrierService {

    private final CarrierRepository repository;

    public CarrierService(CarrierRepository repository) {
        this.repository = repository;
    }

    public Carrier save(Carrier entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Carrier> getAll() {
        List<Carrier> carrierList = new ArrayList<>();
        Iterable<Carrier> iterable = repository.findAll();
        iterable.forEach(carrierList::add);

        return carrierList;
    }
    public Carrier getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No Carrier with ID="+id));
    }

    public Carrier update(Carrier entity) {
        Carrier foundCarrier = this.getById(entity.getID());
        return repository.save(entity);
    }

    public void delete(Carrier entity) {
        repository.delete(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
