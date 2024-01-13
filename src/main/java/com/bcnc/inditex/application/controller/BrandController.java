package com.bcnc.inditex.application.controller;

import com.bcnc.inditex.application.DTO.BrandDTO;
import com.bcnc.inditex.application.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/byId/{brandId}")
    BrandDTO getBrandById(@PathVariable Long brandId) {
        return brandService.findById(brandId);
    }
}
