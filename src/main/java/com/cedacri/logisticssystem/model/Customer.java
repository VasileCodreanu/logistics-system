package com.cedacri.logisticssystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;
    private String directions;
    private String proneNr;
    private String email;
    @Embedded
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(ID, customer.ID) && Objects.equals(name, customer.name) && Objects.equals(directions, customer.directions) && Objects.equals(proneNr, customer.proneNr) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, directions, proneNr, email, address);
    }
}
