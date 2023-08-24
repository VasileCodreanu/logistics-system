package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.model.Sender;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.service.SenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sender")
public class SenderController {
    private final SenderService service;

    public SenderController(SenderService service) {
        this.service = service;
    }

    @PostMapping
    public Sender save(@RequestBody Sender dto){
        return service.save(dto);
    }
    @GetMapping
    public List<Sender> getAll(){
        return service.getAll();
    }
    @GetMapping("/{Id}")
    public Sender getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    public Sender update(@RequestBody Sender dto){
        return service.update(dto);
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        service.deleteById(Id);
    }
}


