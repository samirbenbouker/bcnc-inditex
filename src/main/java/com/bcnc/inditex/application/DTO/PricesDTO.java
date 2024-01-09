package com.bcnc.inditex.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PricesDTO {

    private Long priceId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;

    private Long brandId;
    private Long productId;
}