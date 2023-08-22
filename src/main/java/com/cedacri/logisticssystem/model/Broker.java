package com.cedacri.logisticssystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Broker {
    private Long ID;
    private String brokerName;
    private String phoneNr;
    private BrokerRating brokerRating;

    public enum BrokerRating {
        A, B, C, D
    }
}
