package com.bcnc.inditex.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
public class PricesRequestDTO {

    @NotNull(message = "El campo 'requestDate' es obligatoria")
    private LocalDateTime requestDate;
    private Optional<Long> productId = Optional.empty();
    private Optional<Long> brandId = Optional.empty();

}