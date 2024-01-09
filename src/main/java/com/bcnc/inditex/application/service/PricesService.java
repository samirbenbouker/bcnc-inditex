package com.bcnc.inditex.application.service;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.application.request.PricesRequestDTO;
import com.bcnc.inditex.domain.Prices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PricesService {

    List<PricesDTO> getAll();
    PricesDTO getPricesByStartDateProductIdBrandId(PricesRequestDTO request);

}
