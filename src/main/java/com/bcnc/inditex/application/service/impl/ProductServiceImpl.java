package com.bcnc.inditex.application.service.impl;

import com.bcnc.inditex.application.exception.ProductException;
import com.bcnc.inditex.application.service.ProductService;
import com.bcnc.inditex.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void checkIfExist(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("El producto con el id: " + productId + " no existe"));
    }

}

