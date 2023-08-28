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

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDtoRequest {
    @NotNull(message = "ID should not equal null!")
    @PositiveOrZero(message = "Only positive IDs!")
    private Long dispatcherId;
    @NotNull(message = "ID should not equal null!")
    @PositiveOrZero(message = "Only positive IDs!")
    private Long carrierId;
    @NotNull(message = "ID should not equal null!")
    @PositiveOrZero(message = "Only positive IDs!")
    private Long brokerId;
    @NotNull(message = "ID should not equal null!")
    @PositiveOrZero(message = "Only positive IDs!")
    private Long vehicleId;
    @NotNull(message = "ID should not equal null!")
    @PositiveOrZero(message = "Only positive IDs!")
    private Long senderId;
    @NotNull(message = "ID should not equal null!")
    @PositiveOrZero(message = "Only positive IDs!")
    private Long receiverId;

    @PositiveOrZero(message = "Cannot be negative!")
    private double amountToBePaid;

    @Size(min = 3, message = "Commodity name should be at least 3 chars in length.")
    @NotBlank(message = "Commodity is mandatory")
    private String commodity;
}
