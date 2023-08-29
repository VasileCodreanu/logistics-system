package com.cedacri.logisticssystem.config;

import com.cedacri.logisticssystem.controller.OrderController;
import com.cedacri.logisticssystem.dto.OrderDtoResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class EntityModelAssembler implements RepresentationModelAssembler<OrderDtoResponse, EntityModel<OrderDtoResponse>> {
    @Override
    public EntityModel<OrderDtoResponse> toModel(OrderDtoResponse entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(OrderController.class).getById(entity.getOrderId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getAll()).withRel("orders"));
    }
}
//@Component
//public class EntityModelAssembler<T extends BaseEntity> implements RepresentationModelAssembler<T, EntityModel<T>> {
//    OrderDtoResponse
//    @Override
//    public EntityModel<T> toModel(T entity) {
//
//        return EntityModel.of(entity,
//                linkTo(methodOn(BaseEntity.class).one(employee.getId())).withSelfRel(),
//                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
//    }
//
//        return null;
//    }
//}

