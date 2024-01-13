package com.bcnc.inditex.application.service;

import com.bcnc.inditex.application.DTO.BrandDTO;
import org.springframework.stereotype.Service;

@Service
public interface BrandService {

    BrandDTO findById(Long brandId);

}