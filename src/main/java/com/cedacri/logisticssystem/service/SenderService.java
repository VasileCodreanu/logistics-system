package com.cedacri.logisticssystem.service;

import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.model.Sender;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.repository.SenderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SenderService {
    private final SenderRepository repository;

    public SenderService(SenderRepository repository) {
        this.repository = repository;
    }

    public Sender save(Sender entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Sender> getAll() {
        List<Sender> brokerList = new ArrayList<>();
        Iterable<Sender> iterable = repository.findAll();
        iterable.forEach(brokerList::add);

        return brokerList;
    }

    public Sender getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No Carrier with ID="+id));
    }

    public Sender update(Sender dto) {
        return repository.save(dto);
    }

    public void delete(Sender dto) {
        repository.delete(dto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
