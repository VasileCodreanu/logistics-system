package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Broker;
import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/broker")
@RequiredArgsConstructor
public class BrokerController {

    private final BrokerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Broker save(@RequestBody Broker dto){
        return service.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Broker> getAll(){
        return service.getAll();
    }

    @GetMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public Broker getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Broker update(@RequestBody Broker dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        HttpHeaders header = new HttpHeaders();
        header.add("Delete","Broker with id="+id+" deleted!");
        return new ResponseEntity<> (header, HttpStatus.NO_CONTENT);
    }
}
