package com.cedacri.logisticssystem.controller;


import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer save(@RequestBody Customer dto){
        return service.save(dto);
    }
    @GetMapping
    public List<Customer> getAll(){
        return service.getAll();
    }
    @GetMapping("/{Id}")
    public Customer getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    public Customer update(@RequestBody Customer dto){
        return service.update(dto);
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        service.deleteById(Id);
    }
}
