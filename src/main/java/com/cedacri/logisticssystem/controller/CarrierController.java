package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.service.CarrierService;
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
    public Carrier save(@RequestBody Carrier dto){
        return service.save(dto);
    }

    @GetMapping
    public List<Carrier> getAll(){
        return service.getAll();
    }
    @GetMapping("/{Id}")
    public Carrier getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    public Carrier update(@RequestBody Carrier dto){
        return service.update(dto);
    }

    @DeleteMapping
    //@ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.lic void delete(@RequestBody Carrier dto){
    public void delete(@RequestBody Carrier dto){
        service.delete(dto);
    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        service.deleteById(Id);
    }

}
