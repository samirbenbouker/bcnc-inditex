package com.bcnc.inditex.application.service.impl;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.application.request.PricesRequestDTO;
import com.bcnc.inditex.application.service.PricesService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PricesServiceImpl implements PricesService {

    @Override
    public List<PricesDTO> getAll() {
        return null;
    }

    @Override
    public PricesDTO getPricesByStartDateProductIdBrandId(PricesRequestDTO request) {
        return null;
    }

}
