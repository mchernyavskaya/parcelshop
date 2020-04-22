package com.gls.parcelshop.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {

    @Id
    private Long id;

    @NotNull
    private String parcelNumber;

    private String deliveryDate;
    private DeliveryState deliveryState;
    private Address address;
}
