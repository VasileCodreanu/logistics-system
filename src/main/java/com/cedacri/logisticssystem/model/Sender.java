package com.cedacri.logisticssystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sender extends Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @OneToMany(
            mappedBy = "sender",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Orrder> orderList;

    public void addOrder(Orrder order) {
        if(this.orderList == null){
            orderList = new HashSet<>();
        }
        orderList.add(order);
        order.setSender(this);
    }
    public void removeOrder(Orrder order) {
        orderList.remove(order);
        order.setSender(null);//relly on orphan removal
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sender sender = (Sender) o;
        return Objects.equals(ID, sender.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
