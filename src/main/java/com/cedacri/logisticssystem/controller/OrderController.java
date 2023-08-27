package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.dto.OrderDtoRequest;
import com.cedacri.logisticssystem.dto.OrderDtoResponse;
import com.cedacri.logisticssystem.service.dtoService.OrderDtoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderDtoService dtoService;

  @PostMapping
  public OrderDtoResponse save(@Valid @RequestBody OrderDtoRequest dto){
    return dtoService.save(dto);
  }

  @GetMapping
  public List<OrderDtoResponse> getAll(){
    return dtoService.getAll();
  }

  @GetMapping("/{Id}")
  public OrderDtoResponse getById(@PathVariable Long Id){
    return dtoService.getById(Id);
  }

  @PutMapping
  public OrderDtoResponse update(@RequestBody OrderDtoResponse dto){
    return dtoService.update(dto);
  }

  @DeleteMapping("/{Id}")
  public void deleteById(@PathVariable Long Id){
    dtoService.deleteById(Id);
  }
}
