package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.service.CarrierService;
import com.cedacri.logisticssystem.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vehicle")
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public Vehicle save(@RequestBody Vehicle dto){
        return service.save(dto);
    }

    @GetMapping
    public List<Vehicle> getAll(){
        return service.getAll();
    }
    @GetMapping("/{Id}")
    public Vehicle getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    public Vehicle update(@RequestBody Vehicle dto){
        return service.update(dto);
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        service.deleteById(Id);
    }
}
