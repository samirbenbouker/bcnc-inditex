package com.bcnc.inditex.application.controller;

import com.bcnc.inditex.application.DTO.ProductDTO;
import com.bcnc.inditex.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/byId/{productId}")
    ProductDTO getProductById(@PathVariable Long productId) {
        return productService.findById(productId);
    }

}
