package com.cedacri.logisticssystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Receiver extends Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @OneToMany(
            mappedBy = "receiver",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Orrder> orderList;

    public void addOrder(Orrder order) {
        if(this.orderList == null){
            orderList = new HashSet<>();
        }
        orderList.add(order);
        order.setReceiver(this);
    }
    public void removeOrder(Orrder order) {
        orderList.remove(order);
        order.setReceiver(null);//relly on orphan removal
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(ID, receiver.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
