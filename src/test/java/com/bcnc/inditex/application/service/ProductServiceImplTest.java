package com.bcnc.inditex.application.service;

import com.bcnc.inditex.application.DTO.ProductDTO;
import com.bcnc.inditex.application.exception.ProductException;
import com.bcnc.inditex.application.service.impl.ProductServiceImpl;
import com.bcnc.inditex.domain.Product;
import com.bcnc.inditex.infrastructure.repository.ProductRepository;
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

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getByIdTest() {
        // mock data
        Product expectedProduct = new Product(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));

        // Call the method
        ProductDTO result = productService.findById(1L);

        // verify the result
        assertEquals(expectedProduct.getProductId(), result.getProductId());
    }

    @Test
    public void getByIdProductExceptionTest() {
        // Mock data for return empty value
        when(productRepository.findById(eq(1L))).thenReturn(Optional.empty());

        // Call the method
        // use assertThrows for check if ProductException its executed
        ProductException exception = assertThrows(ProductException.class, () -> productService.findById(1L));

        // Verify the result
        assertEquals("El producto con el id: 1 no existe", exception.getMessage());

        // Check if productRepository called with the good arguments
        verify(productRepository).findById(eq(1L));
    }


}
