package com.cedacri.logisticssystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sender extends Customer{

    @OneToMany(
            mappedBy = "sender",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Orrder> orderList = new HashSet<>();

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

}
