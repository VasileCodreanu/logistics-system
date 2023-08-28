package com.cedacri.logisticssystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @OneToMany(
            mappedBy = "vehicle",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<Order> orderList = new HashSet<>();;

    private String vehicleNr;
    private String currentCityLocation;
//    @Enumerated(EnumType.STRING)
    private VehicleStatus currentStatus;

    public enum VehicleStatus {
        FREE, BUSY, INACTIVE;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
        order.setVehicle(this);
    }
    public void removeOrder(Order order) {
        orderList.remove(order);
        order.setVehicle(null);//relly on orphan removal
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(ID, vehicle.ID) && Objects.equals(vehicleNr, vehicle.vehicleNr) && Objects.equals(currentCityLocation, vehicle.currentCityLocation) && currentStatus == vehicle.currentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, vehicleNr, currentCityLocation, currentStatus);
    }
}

