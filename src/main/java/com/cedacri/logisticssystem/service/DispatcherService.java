package com.cedacri.logisticssystem.service;

import com.cedacri.logisticssystem.exceptions.customExceptions.EntityNotFoundException;
import com.cedacri.logisticssystem.model.Dispatcher;
import com.cedacri.logisticssystem.repository.DispatcherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispatcherService {

    private final DispatcherRepository repository;

    public DispatcherService(DispatcherRepository repository) {
        this.repository = repository;
    }

    public Dispatcher save(Dispatcher entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Dispatcher> getAll() {
        List<Dispatcher> carrierList = new ArrayList<>();
        Iterable<Dispatcher> iterable = repository.findAll();
        iterable.forEach(carrierList::add);

        return carrierList;
    }
    public Dispatcher getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Dispatcher with id=", id));
    }

    public Dispatcher update(Dispatcher entity) {
        this.getById(entity.getID());
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
