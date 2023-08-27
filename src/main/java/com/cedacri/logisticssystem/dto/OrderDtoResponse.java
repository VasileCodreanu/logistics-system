package com.cedacri.logisticssystem.dto;

import com.cedacri.logisticssystem.model.Order.OrderStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class OrderDtoResponse {
  private Long orderId;
  private Long dispatcherId;
  private Long carrierId;
  private Long brokerId;
  private Long vehicleId;
  private Long senderId;
  private Long receiverId;

  private double amountToBePaid;
  private String commodity;
  private LocalDate orderPlacedOn;
  private LocalDate orderDeliveredOn;
  private OrderStatus orderStatus;
}
