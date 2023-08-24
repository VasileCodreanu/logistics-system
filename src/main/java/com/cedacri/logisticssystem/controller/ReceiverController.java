package com.cedacri.logisticssystem.controller;


import com.cedacri.logisticssystem.model.Receiver;
import com.cedacri.logisticssystem.service.ReceiverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/receiver")
public class ReceiverController {
    private final ReceiverService service;

    public ReceiverController(ReceiverService service) {
        this.service = service;
    }

    @PostMapping
    public Receiver save(@RequestBody Receiver dto){
        return service.save(dto);
    }
    @GetMapping
    public List<Receiver> getAll(){
        return service.getAll();
    }
    @GetMapping("/{Id}")
    public Receiver getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    public Receiver update(@RequestBody Receiver dto){
        return service.update(dto);
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        service.deleteById(Id);
    }
}
