package com.cedacri.logisticssystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public class Orrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId because we want the child table row to share the Primary Key with its parent table row meaning that the Primary Key is also a Foreign Key back to the parent table record.
    @JoinColumn(name = "carrier_id", referencedColumnName = "ID")
    private Carrier carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", referencedColumnName = "ID")
    private Broker broker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "ID")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "ID")
    private Sender sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "ID")
    private Receiver receiver;

    private double amountToBePaid;
    private String commodity;
    private LocalDate orderPlacedOn;
    private LocalDate orderDeliveredOn;
    private OrderStatus orderStatus;

    public enum OrderStatus {
        DELIVERED, PROCESSING, CANCELLED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orrder order = (Orrder) o;
        return Double.compare(order.amountToBePaid, amountToBePaid) == 0 && Objects.equals(ID, order.ID) && Objects.equals(commodity, order.commodity) && Objects.equals(orderPlacedOn, order.orderPlacedOn) && Objects.equals(orderDeliveredOn, order.orderDeliveredOn) && orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, amountToBePaid, commodity, orderPlacedOn, orderDeliveredOn, orderStatus);
    }
}
