package com.bcnc.inditex.application.service.impl;

import com.bcnc.inditex.application.exception.BrandException;
import com.bcnc.inditex.application.service.BrandService;
import com.bcnc.inditex.infrastructure.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public void checkIfExist(Long brandId) {
        brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandException("La marca con el id: " + brandId + " no existe"));
    }

}