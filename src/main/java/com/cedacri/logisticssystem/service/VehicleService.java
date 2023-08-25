package com.cedacri.logisticssystem.service;

import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.repository.CarrierRepository;
import com.cedacri.logisticssystem.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle save(Vehicle entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Vehicle> getAll() {
        List<Vehicle> carrierList = new ArrayList<>();
        Iterable<Vehicle> iterable = repository.findAll();
        iterable.forEach(carrierList::add);

        return carrierList;
    }
    public Vehicle getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No Vehicle with ID="+id));
    }

    public Vehicle update(Vehicle entity) {
        Vehicle foundVehicle = this.getById(entity.getID());
        return repository.save(foundVehicle);
    }

    public void delete(Vehicle entity) {
        repository.delete(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
