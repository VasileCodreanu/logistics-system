package com.cedacri.logisticssystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    Set<Order> orderList = new HashSet<>();;
}
