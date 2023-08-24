package com.cedacri.logisticssystem.service;

import com.cedacri.logisticssystem.model.Receiver;
import com.cedacri.logisticssystem.model.Sender;
import com.cedacri.logisticssystem.repository.ReceiverRepository;
import com.cedacri.logisticssystem.repository.SenderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiverService {
    private final ReceiverRepository repository;

    public ReceiverService(ReceiverRepository repository) {
        this.repository = repository;
    }

    public Receiver save(Receiver entity){//carrierDto to Carrier
        return repository.save(entity);
    }

    public List<Receiver> getAll() {
        List<Receiver> brokerList = new ArrayList<>();
        Iterable<Receiver> iterable = repository.findAll();
        iterable.forEach(brokerList::add);

        return brokerList;
    }

    public Receiver getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No Carrier with ID="+id));
    }

    public Receiver update(Receiver dto) {
        return repository.save(dto);
    }

    public void delete(Receiver dto) {
        repository.delete(dto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
