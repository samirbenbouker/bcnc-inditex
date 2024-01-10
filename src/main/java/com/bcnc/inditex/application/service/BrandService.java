package com.bcnc.inditex.application.service;

import org.springframework.stereotype.Service;

@Service
public interface BrandService {

    void checkIfExist(Long brandId);

}