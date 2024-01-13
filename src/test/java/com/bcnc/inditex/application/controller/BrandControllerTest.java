package com.bcnc.inditex.application.controller;

import com.bcnc.inditex.application.DTO.BrandDTO;
import com.bcnc.inditex.application.mapper.BrandMapper;
import com.bcnc.inditex.application.service.BrandService;
import com.bcnc.inditex.domain.Brand;
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

public class BrandControllerTest {

    @Mock
    private BrandService brandService;

    @InjectMocks
    private BrandController brandController;

    private MockMvc mockMvc;
    private BrandMapper brandMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        brandMapper = Mappers.getMapper(BrandMapper.class);
        this.mockMvc = standaloneSetup(brandController).build();
    }

    @Test
    public void getBrandByIdTest() throws Exception {
        mockMvc = standaloneSetup(brandController).build();

        // Mocking the service response
        Long brandId = 1L;
        BrandDTO mockBrandDTO = brandMapper.entityToDTO(new Brand(1L, "Zara"));
        when(brandService.findById(brandId)).thenReturn(mockBrandDTO);

        // Performing the test
        mockMvc.perform(MockMvcRequestBuilders.get("/brand/byId/{brandId}", mockBrandDTO.getBrandId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(mockBrandDTO.getBrandId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(mockBrandDTO.getName()));
    }

}
