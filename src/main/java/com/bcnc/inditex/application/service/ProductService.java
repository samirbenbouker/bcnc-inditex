package com.bcnc.inditex.application.service;

import com.bcnc.inditex.application.DTO.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ProductDTO findById(Long productId);

}
