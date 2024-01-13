package com.bcnc.inditex.application.service.impl;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.application.mapper.PricesMapper;
import com.bcnc.inditex.application.request.PricesRequestDTO;
import com.bcnc.inditex.application.service.BrandService;
import com.bcnc.inditex.application.service.PricesService;
import com.bcnc.inditex.application.service.ProductService;
import com.bcnc.inditex.infrastructure.repository.PricesRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PricesServiceImpl implements PricesService {

    private PricesMapper pricesMapper = Mappers.getMapper(PricesMapper.class);

    @Autowired
    private PricesRepository pricesRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;

    @Override
    public List<PricesDTO> getAll() {
        return pricesRepository.findAll().stream()
                .map(pricesMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PricesDTO getPricesByStartDateProductIdBrandId(PricesRequestDTO pricesRequestDTO) {
        Long brandId = pricesRequestDTO.getBrandId().orElse(1L);
        Long productId = pricesRequestDTO.getProductId().orElse(35455L);

        // Will see if brand and product exist
        brandService.findById(brandId);
        productService.findById(productId);

        // Make a query for get prices with request parameters
        return pricesMapper.entityToDTO(pricesRepository.findByRequestDateBrandIdProductId(pricesRequestDTO.getRequestDate(), brandId, productId));
    }

}
