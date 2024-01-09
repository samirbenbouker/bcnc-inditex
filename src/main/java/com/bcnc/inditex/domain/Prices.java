package com.bcnc.inditex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class Prices {

    @Id
    private Long priceId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priority;
    private Double price;
    private String curr; // In that case I will use String, but in a real project I would use an Enum, with all the currencies where Inditex performs

    private Long brandId;
    private Long productId;

}
