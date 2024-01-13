package com.bcnc.inditex.application.service;

import com.bcnc.inditex.application.DTO.BrandDTO;
import com.bcnc.inditex.application.exception.BrandException;
import com.bcnc.inditex.application.service.impl.BrandServiceImpl;
import com.bcnc.inditex.domain.Brand;
import com.bcnc.inditex.infrastructure.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getByIdTest() {
        // mock data
        Brand expectedBrand = new Brand(1L, "Zara");
        when(brandRepository.findById(1L)).thenReturn(Optional.of(expectedBrand));

        // Call the method
        BrandDTO result = brandService.findById(1L);

        // verify the result
        assertEquals(expectedBrand.getBrandId(), result.getBrandId());
        assertEquals(expectedBrand.getName(), result.getName());
    }

    @Test
    public void getByIdBrandExceptionTest() {
        // Mock data for return empty value
        when(brandRepository.findById(eq(1L))).thenReturn(Optional.empty());

        // Call the method
        // use assertThrows for check if BrandException its executed
        BrandException exception = assertThrows(BrandException.class, () -> brandService.findById(1L));

        // Verify the result
        assertEquals("La marca con el id: 1 no existe", exception.getMessage());

        // Check if brandRepository called with the good arguments
        verify(brandRepository).findById(eq(1L));
    }

}
