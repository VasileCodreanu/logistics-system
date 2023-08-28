package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.service.CarrierService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carrier")
public class CarrierController {

    private final CarrierService service;

    public CarrierController(CarrierService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carrier save(@RequestBody Carrier dto){
        return service.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Carrier> getAll(){
        return service.getAll();
    }

    @GetMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public Carrier getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Carrier update(@RequestBody Carrier dto){
        return service.update(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        HttpHeaders header = new HttpHeaders();
        header.add("Delete","Carrier with id="+id+" deleted!");
        return new ResponseEntity<> (header, HttpStatus.NO_CONTENT);
    }
}
