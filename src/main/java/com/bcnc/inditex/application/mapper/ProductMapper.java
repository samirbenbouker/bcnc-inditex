package com.bcnc.inditex.application.mapper;

import com.bcnc.inditex.application.DTO.ProductDTO;
import com.bcnc.inditex.domain.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDTO entityToDTO(Product product);

}
