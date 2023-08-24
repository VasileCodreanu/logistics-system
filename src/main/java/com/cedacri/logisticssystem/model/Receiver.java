package com.cedacri.logisticssystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receiver extends Customer{

    @OneToMany(
            mappedBy = "receiver",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Orrder> orderList = new HashSet<>();

    public void addOrder(Orrder order) {
        orderList.add(order);
        order.setReceiver(this);
    }
    public void removeOrder(Orrder order) {
        orderList.remove(order);
        order.setReceiver(null);//relly on orphan removal
    }


}
