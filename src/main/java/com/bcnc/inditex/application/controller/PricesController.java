package com.bcnc.inditex.application.controller;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.application.request.PricesRequestDTO;
import com.bcnc.inditex.application.service.PricesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @GetMapping()
    public List<PricesDTO> getAll() {
        return pricesService.getAll();
    }

    @GetMapping(value = "/filter")
    public PricesDTO getPricesByStartDateProductIdBrandId(@RequestBody @Valid PricesRequestDTO pricesRequestDTO) {
        return pricesService.getPricesByStartDateProductIdBrandId(pricesRequestDTO);
    }

}