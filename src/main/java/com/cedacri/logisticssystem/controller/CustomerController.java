package com.cedacri.logisticssystem.controller;


import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody Customer dto){
        return service.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAll(){
        return service.getAll();
    }

    @GetMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer update(@RequestBody Customer dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        HttpHeaders header = new HttpHeaders();
        header.add("Delete","Customer with id="+id+" deleted!");
        return new ResponseEntity<> (header, HttpStatus.NO_CONTENT);
    }
}
