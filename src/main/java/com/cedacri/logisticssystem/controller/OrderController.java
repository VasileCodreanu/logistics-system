package com.cedacri.logisticssystem.controller;

import com.cedacri.logisticssystem.config.EntityModelAssembler;
import com.cedacri.logisticssystem.dto.OrderDtoRequest;
import com.cedacri.logisticssystem.dto.OrderDtoResponse;
import com.cedacri.logisticssystem.service.dtoService.OrderDtoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderDtoService dtoService;
  private final EntityModelAssembler assembler;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntityModel<OrderDtoResponse> save(@Valid @RequestBody OrderDtoRequest dto){
    return assembler.toModel(dtoService.save(dto));
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CollectionModel<EntityModel<OrderDtoResponse>> getAll(){
    List<EntityModel<OrderDtoResponse>> employees = dtoService.getAll().stream()
            .map(e -> assembler.toModel(e))
            .toList();
    return  CollectionModel.of(employees, linkTo(methodOn(OrderController.class).getAll()).withSelfRel());
  }

  @GetMapping("/{Id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityModel<OrderDtoResponse> getById(@PathVariable Long Id){
    return assembler.toModel(dtoService.getById(Id));
  }

  @PutMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public EntityModel<OrderDtoResponse> update(@RequestBody OrderDtoResponse dto){
    return assembler.toModel(dtoService.update(dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id){
    dtoService.deleteById(id);
    HttpHeaders header = new HttpHeaders();
    header.add("Delete","Order with id="+id+" deleted!");
    return new ResponseEntity<> (header, HttpStatus.NO_CONTENT);
  }
}
