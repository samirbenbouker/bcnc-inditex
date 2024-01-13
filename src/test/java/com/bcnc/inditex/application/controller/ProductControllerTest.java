package com.bcnc.inditex.application.controller;

import com.bcnc.inditex.application.DTO.ProductDTO;
import com.bcnc.inditex.application.mapper.ProductMapper;
import com.bcnc.inditex.application.service.ProductService;
import com.bcnc.inditex.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productMapper = Mappers.getMapper(ProductMapper.class);
        this.mockMvc = standaloneSetup(productController).build();
    }

    @Test
    public void getProductByIdTest() throws Exception {
        mockMvc = standaloneSetup(productController).build();

        // Mocking the service response
        Long productId = 1L;
        ProductDTO mockProductDTO = productMapper.entityToDTO(new Product(1L));
        when(productService.findById(productId)).thenReturn(mockProductDTO);

        // Performing the test
        mockMvc.perform(MockMvcRequestBuilders.get("/product/byId/{productId}", mockProductDTO.getProductId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(mockProductDTO.getProductId()));
    }

}
