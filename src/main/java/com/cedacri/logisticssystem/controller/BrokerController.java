package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.model.Broker;
import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.service.BrokerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/broker")
public class BrokerController {

    private final BrokerService service;

    public BrokerController(BrokerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Broker save(@RequestBody Broker dto){
        return service.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Broker> getAll(){
        return service.getAll();
    }

    @GetMapping("/{Id}")
    @ResponseStatus(HttpStatus.OK)
    public Broker getById(@PathVariable Long Id){
        return service.getById(Id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Broker update(@RequestBody Broker dto){
        return service.update(dto);
    }

    @DeleteMapping
    //@ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.lic void delete(@RequestBody Carrier dto){
    public void delete(@RequestBody Broker dto){
        service.delete(dto);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
//        dtoService.deleteCategoryById(id);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Delete Category","Category with id="+id+" deleted!");
//
//        return new ResponseEntity<> (headers, HttpStatus.ACCEPTED);
//
//    }

    @DeleteMapping("/{Id}")
    public void deleteById(@PathVariable Long Id){
        service.deleteById(Id);
    }
}
