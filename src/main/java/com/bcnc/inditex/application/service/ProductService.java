package com.bcnc.inditex.application.service;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    void checkIfExist(Long productId);

}
