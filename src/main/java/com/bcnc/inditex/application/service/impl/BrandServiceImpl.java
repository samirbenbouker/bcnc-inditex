package com.bcnc.inditex.application.service.impl;

import com.bcnc.inditex.application.DTO.BrandDTO;
import com.bcnc.inditex.application.exception.BrandException;
import com.bcnc.inditex.application.mapper.BrandMapper;
import com.bcnc.inditex.application.service.BrandService;
import com.bcnc.inditex.infrastructure.repository.BrandRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BrandServiceImpl implements BrandService {

    private BrandMapper brandMapper = Mappers.getMapper(BrandMapper.class);

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandDTO findById(Long brandId) {
        return brandMapper.entityToDTO(brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandException("La marca con el id: " + brandId + " no existe")));
    }

}