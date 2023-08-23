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

    @Embedded
    private Address address;
    private String name;
    private String directions;
    private String proneNr;
    private String email;

}
