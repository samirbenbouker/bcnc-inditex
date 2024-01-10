package com.bcnc.inditex.application.mapper;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.domain.Prices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PricesMapper {

    @Mapping(target = "prices.priority", ignore = true)
    @Mapping(target = "prices.curr", ignore = true)
    PricesDTO entityToDTO(Prices prices);

}
