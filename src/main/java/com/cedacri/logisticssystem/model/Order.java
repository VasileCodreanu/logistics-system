package com.cedacri.logisticssystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long ID;
    private Customer sender;
    private Customer receiver;
    private Broker broker;
    private double amountToBePaid;
    private Commodity commodity;

    private Vehicle vehicle;
    private LocalDate orderPlacedOn;
    private LocalDate orderDeliveredOn;
    private OrderStatus orderStatus;

    public enum OrderStatus {
        DELIVERED, PROCESSING, CANCELLED;
    }
}
