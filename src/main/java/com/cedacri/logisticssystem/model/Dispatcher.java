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
@AllArgsConstructor
@NoArgsConstructor
public class Dispatcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String firstName;
    private String lastName;
    private String phoneNr;
    private String email;
    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "dispatcher",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Orrder> dispatcherOrders = new HashSet<>();;
}
