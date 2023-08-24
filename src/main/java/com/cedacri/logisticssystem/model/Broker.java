package com.cedacri.logisticssystem.model;

import jakarta.persistence.*;
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
public class Broker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @OneToMany(
            mappedBy = "broker",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Orrder> orderList;

    private String brokerName;
    private String phoneNr;
//    @Enumerated(EnumType.STRING)
    private BrokerRating brokerRating;
    @Embedded
    private Address address;

    public void addOrder(Orrder order) {
        if(this.orderList == null){
            orderList = new HashSet<>();
        }
        orderList.add(order);
        order.setBroker(this);
    }
    public void removeOrder(Orrder order) {
        orderList.remove(order);
        order.setBroker(null);//relly on orphan removal
    }

    public enum BrokerRating {
        A, B, C, D
    }
}
