package com.cedacri.logisticssystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnore
    @OneToMany(
            mappedBy = "broker",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("broker")
    private Set<Order> orderList;

    private String brokerName;
    private String phoneNr;
//    @Enumerated(EnumType.STRING)
    private BrokerRating brokerRating;
    @Embedded
    private Address address;

    public void addOrder(Order order) {
        if(this.orderList == null){
            orderList = new HashSet<>();
        }
        orderList.add(order);
        order.setBroker(this);
    }
    public void removeOrder(Order order) {
        orderList.remove(order);
        order.setBroker(null);//relly on orphan removal
    }

    public enum BrokerRating {
        A, B, C, D
    }
}
