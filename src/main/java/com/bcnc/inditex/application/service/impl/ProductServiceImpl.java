package com.bcnc.inditex.application.service.impl;

import com.bcnc.inditex.application.DTO.ProductDTO;
import com.bcnc.inditex.application.exception.ProductException;
import com.bcnc.inditex.application.mapper.ProductMapper;
import com.bcnc.inditex.application.service.ProductService;
import com.bcnc.inditex.infrastructure.repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductServiceImpl implements ProductService {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO findById(Long productId) {
        return productMapper.entityToDTO(productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("El producto con el id: " + productId + " no existe")));
    }

}

