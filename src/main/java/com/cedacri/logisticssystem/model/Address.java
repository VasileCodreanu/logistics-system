package com.cedacri.logisticssystem.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
public class Address {
    private String city;
    private String address;
}
