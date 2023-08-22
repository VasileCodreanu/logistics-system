package com.cedacri.logisticssystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commodity {
    private Long ID;
    private String name;
    private int length;
    private int weight;
}
