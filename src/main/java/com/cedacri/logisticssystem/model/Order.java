package com.cedacri.logisticssystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_table" )
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatcher_id", referencedColumnName = "ID")
    private Dispatcher dispatcher;

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId because we want the child table row to share the Primary Key with its parent table row meaning that the Primary Key is also a Foreign Key back to the parent table record.
    @JoinColumn(name = "carrier_id", referencedColumnName = "ID")
    private Carrier carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", referencedColumnName = "ID")
    @JsonIgnoreProperties("orderList")//will prevent fields from being serialized/deserialized.
    private Broker broker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "ID")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "ID")
    private Customer sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "ID")
    private Customer receiver;

    private double amountToBePaid;
    private String commodity;
    private LocalDate orderPlacedOn;
    private LocalDate orderDeliveredOn;
    private OrderStatus orderStatus;

    public Order(Dispatcher dispatcher, Carrier carrier, Broker broker, Vehicle vehicle,
        Customer sender, Customer receiver, double amountToBePaid, String commodity,
        LocalDate orderPlacedOn, LocalDate orderDeliveredOn, OrderStatus orderStatus) {
        this.dispatcher = dispatcher;
        this.carrier = carrier;
        this.broker = broker;
        this.vehicle = vehicle;
        this.sender = sender;
        this.receiver = receiver;
        this.amountToBePaid = amountToBePaid;
        this.commodity = commodity;
        this.orderPlacedOn = orderPlacedOn;
        this.orderDeliveredOn = orderDeliveredOn;
        this.orderStatus = orderStatus;
    }

    public enum OrderStatus {
        DELIVERED, PROCESSING, CANCELLED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.amountToBePaid, amountToBePaid) == 0 && Objects.equals(ID, order.ID) && Objects.equals(commodity, order.commodity) && Objects.equals(orderPlacedOn, order.orderPlacedOn) && Objects.equals(orderDeliveredOn, order.orderDeliveredOn) && orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, amountToBePaid, commodity, orderPlacedOn, orderDeliveredOn, orderStatus);
    }
}
