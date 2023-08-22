package com.cedacri.logisticssystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long ID;
    private String companyName;
    private Address address;
    private String proneNr;
    private String email;
}
