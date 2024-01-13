package com.bcnc.inditex.application.mapper;

import com.bcnc.inditex.application.DTO.BrandDTO;
import com.bcnc.inditex.domain.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {

    BrandDTO entityToDTO(Brand brand);
}

