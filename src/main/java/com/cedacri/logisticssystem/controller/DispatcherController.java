package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Dispatcher;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.service.DispatcherService;
import com.cedacri.logisticssystem.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dispatcher")
public class DispatcherController {
    private final DispatcherService service;

    public DispatcherController(DispatcherService service) {
        this.service = service;
    }

    @PostMapping
    public Dispatcher save(@RequestBody Dispatcher dto){
        return service.save(dto);
    }

    @GetMapping
    public List<Dispatcher> getAll(){
        return service.getAll();
    }

    @GetMapping("/{Id}")
    public Dispatcher getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    public Dispatcher update(@RequestBody Dispatcher dto){
        return service.update(dto);
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        service.deleteById(Id);
    }
}
