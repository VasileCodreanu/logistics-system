package com.cedacri.logisticssystem.service.dtoService;

import com.cedacri.logisticssystem.dto.OrderDtoRequest;
import com.cedacri.logisticssystem.dto.OrderDtoResponse;
import com.cedacri.logisticssystem.model.Broker;
import com.cedacri.logisticssystem.model.Carrier;
import com.cedacri.logisticssystem.model.Customer;
import com.cedacri.logisticssystem.model.Dispatcher;
import com.cedacri.logisticssystem.model.Order;
import com.cedacri.logisticssystem.model.Order.OrderStatus;
import com.cedacri.logisticssystem.model.Vehicle;
import com.cedacri.logisticssystem.service.BrokerService;
import com.cedacri.logisticssystem.service.CarrierService;
import com.cedacri.logisticssystem.service.CustomerService;
import com.cedacri.logisticssystem.service.DispatcherService;
import com.cedacri.logisticssystem.service.VehicleService;
import com.cedacri.logisticssystem.service.managerService.OrderManagerService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDtoService {

  private final OrderManagerService manager;
  private final DispatcherService dispatcherService;
  private final CarrierService carrierService;
  private final BrokerService brokerService;
  private final VehicleService vehicleService;
  private final CustomerService customerService;

  public OrderDtoResponse save(OrderDtoRequest dto) {
    Dispatcher dispatcher = dispatcherService.getById(dto.getDispatcherId());
    Carrier carrier   = carrierService.getById(dto.getCarrierId());
    Broker broker     = brokerService.getById(dto.getBrokerId());
    Vehicle vehicle   = vehicleService.getById(dto.getVehicleId());
    Customer sender   = customerService.getById(dto.getSenderId());
    Customer receiver = customerService.getById(dto.getReceiverId());

    Order order = manager.save(
        Order.builder()
            .dispatcher(dispatcher)
            .carrier(carrier)
            .broker(broker)
            .vehicle(vehicle)
            .sender(sender)
            .receiver(receiver)
            .amountToBePaid(dto.getAmountToBePaid())
            .commodity(dto.getCommodity())
            .orderPlacedOn(LocalDate.now())
            .orderStatus(OrderStatus.PROCESSING)
            .orderDeliveredOn(null)
            .build()
    );

    Order savedOrder = manager.save(order);
    return this.mapOrderToOrderDtoResponse(savedOrder);
  }

  public List<OrderDtoResponse> getAll() {
    List<Order> orders = manager.getAll();
    return orders.stream()
        .map(order -> this.mapOrderToOrderDtoResponse(order))
        .toList();
  }

  public OrderDtoResponse getById(Long id) {
    Order order = manager.getById(id);
    return this.mapOrderToOrderDtoResponse(order);
  }

  public OrderDtoResponse update(OrderDtoResponse dto) {

    Order mappedOrder = mapOrderDtoResponseToOrder(dto);
    Order order = manager.update(mappedOrder);
    return this.mapOrderToOrderDtoResponse(order);
  }

  public void deleteById(Long id) {
    manager.deleteById(id);
  }
  private Order mapOrderDtoResponseToOrder(OrderDtoResponse dto){

    Dispatcher dispatcher = dispatcherService.getById(dto.getDispatcherId());
    Carrier carrier   = carrierService.getById(dto.getCarrierId());
    Broker broker     = brokerService.getById(dto.getBrokerId());
    Vehicle vehicle   = vehicleService.getById(dto.getVehicleId());
    Customer sender   = customerService.getById(dto.getSenderId());
    Customer receiver = customerService.getById(dto.getReceiverId());

    return Order.builder()
        .ID(dto.getOrderId())
        .dispatcher(dispatcher)
        .carrier(carrier)
        .broker(broker)
        .vehicle(vehicle)
        .sender(sender)
        .receiver(receiver)
        .amountToBePaid(dto.getAmountToBePaid())
        .commodity(dto.getCommodity())
        .orderPlacedOn(dto.getOrderPlacedOn())
        .orderDeliveredOn(dto.getOrderDeliveredOn())
        .orderStatus(dto.getOrderStatus())
        .build();
  }

  private OrderDtoResponse mapOrderToOrderDtoResponse(Order o){
        return OrderDtoResponse.builder()
        .orderId(null == o.getID() ? null : o.getID())
        .dispatcherId(o.getDispatcher().getID())
        .carrierId(o.getCarrier().getID())
        .brokerId(o.getBroker().getID())
        .vehicleId(o.getVehicle().getID())
        .senderId(o.getSender().getID())
        .receiverId(o.getReceiver().getID())
        .amountToBePaid(o.getAmountToBePaid())
        .commodity(o.getCommodity())
        .orderPlacedOn(o.getOrderPlacedOn())
        .orderDeliveredOn(o.getOrderDeliveredOn())
        .orderStatus(o.getOrderStatus())
        .build();
  }
}
