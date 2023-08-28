package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Dispatcher;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.service.DispatcherService;
import com.cedacri.logisticssystem.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dispatcher")
@RequiredArgsConstructor
public class DispatcherController {
    private final DispatcherService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dispatcher save(@RequestBody Dispatcher dto){
        return service.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dispatcher> getAll(){
        return service.getAll();
    }

    @GetMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public Dispatcher getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Dispatcher update(@RequestBody Dispatcher dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        HttpHeaders header = new HttpHeaders();
        header.add("Delete","Dispatcher with id="+id+" deleted!");
        return new ResponseEntity<> (header, HttpStatus.NO_CONTENT);
    }
}
