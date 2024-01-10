package com.bcnc.inditex.application.service;

import com.bcnc.inditex.application.exception.ProductException;
import com.bcnc.inditex.application.service.impl.ProductServiceImpl;
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
    public void checkIfExistProductExceptionTest() {
        // Mock data for return empty value
        when(productRepository.findById(eq(1L))).thenReturn(Optional.empty());

        // Call the method
        // use assertThrows for check if ProductException its executed
        ProductException exception = assertThrows(ProductException.class, () -> productService.checkIfExist(1L));

        // Verify the result
        assertEquals("El producto con el id: 1 no existe", exception.getMessage());

        // Check if productRepository called with the good arguments
        verify(productRepository).findById(eq(1L));
    }


}
