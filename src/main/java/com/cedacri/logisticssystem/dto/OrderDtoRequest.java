package com.cedacri.logisticssystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import org.antlr.v4.runtime.misc.NotNull;
//import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDtoRequest {
    @NotNull(message = "ID should not equal null!")
    @PositiveOrZero(message = "Only positiv IDs!")
    private Long dispatcherId;
    private Long carrierId;
    private Long brokerId;
    private Long vehicleId;
    private Long senderId;
    private Long receiverId;

    private double amountToBePaid;

    @Size(min = 3, message = "CommodityId Name should be at least 2 chars in length.")
    @NotBlank(message = "CommodityId is mandatory")
    private String commodity;

}
