package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.dto.OrderDtoRequest;
import com.cedacri.logisticssystem.dto.OrderDtoResponse;
import com.cedacri.logisticssystem.service.dtoService.OrderDtoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderDtoService dtoService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDtoResponse save(@Valid @RequestBody OrderDtoRequest dto){
    return dtoService.save(dto);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<OrderDtoResponse> getAll(){
    return dtoService.getAll();
  }

  @GetMapping("/{Id}")
  @ResponseStatus(HttpStatus.OK)
  public OrderDtoResponse getById(@PathVariable Long Id){
    return dtoService.getById(Id);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public OrderDtoResponse update(@RequestBody OrderDtoResponse dto){
    return dtoService.update(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id){
    dtoService.deleteById(id);
    HttpHeaders header = new HttpHeaders();
    header.add("Delete","Order with id="+id+" deleted!");
    return new ResponseEntity<> (header, HttpStatus.NO_CONTENT);
  }
}
