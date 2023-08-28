package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.service.CarrierService;
import com.cedacri.logisticssystem.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle save(@RequestBody Vehicle dto){
        return service.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getAll(){
        return service.getAll();
    }

    @GetMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Vehicle update(@RequestBody Vehicle dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        HttpHeaders header = new HttpHeaders();
        header.add("Delete","Vehicle with id="+id+" deleted!");
        return new ResponseEntity<> (header, HttpStatus.NO_CONTENT);
    }
}
