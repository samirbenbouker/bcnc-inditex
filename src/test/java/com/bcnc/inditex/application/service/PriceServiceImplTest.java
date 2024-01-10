package com.bcnc.inditex.application.service;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.application.request.PricesRequestDTO;
import com.bcnc.inditex.application.service.impl.PricesServiceImpl;
import com.bcnc.inditex.domain.Prices;
import com.bcnc.inditex.infrastructure.repository.PricesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PriceServiceImplTest {

    @Mock
    private PricesRepository pricesRepository;
    @Mock
    private BrandService brandService;
    @Mock
    private ProductService productService;
    @InjectMocks
    private PricesServiceImpl pricesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        // Mock data
        List<Prices> mockPrices = createMockPricesList();
        when(pricesRepository.findAll()).thenReturn(mockPrices);

        // Call the method
        List<PricesDTO> result = pricesService.getAll();

        // Verify the result
        assertEquals(4, result.size());
    }

    @Test
    public void filterTest1() {
        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // Mock request
        PricesRequestDTO requestDTO = getPricesRequestDTO(LocalDateTime.of(2020, 6, 14, 10, 0, 0), Optional.empty(), Optional.empty());

        // Mock data
        List<Prices> mockPrices = createMockPricesList();
        Prices expectedResult = createPrices(1L, LocalDateTime.of(2020, 6, 14, 00, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L, 35455L, 1L, 35.50, "EUR");

        Long brandId = requestDTO.getBrandId().orElse(1L);
        Long productId = requestDTO.getProductId().orElse(35455L);

        when(pricesRepository.findByStartDateBrandIdProductId(requestDTO.getRequestDate(), brandId, productId))
                .thenReturn(mockPrices.stream()
                        .filter(price -> Objects.equals(price.getProductId(), productId)
                                && Objects.equals(price.getBrandId(), brandId)
                                && requestDateBetweenStartDateEndDate(requestDTO.getRequestDate(), price.getStartDate(), price.getEndDate()))
                        .collect(Collectors.toList()));

        // Call the method
        PricesDTO result = pricesService.getPricesByStartDateProductIdBrandId(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult.getPriceId(), result.getPriceId());
    }

    @Test
    public void filterTest2() {
        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // Mock request
        PricesRequestDTO requestDTO = getPricesRequestDTO(LocalDateTime.of(2020, 6, 14, 16, 0, 0), Optional.empty(), Optional.empty());

        // Mock data
        List<Prices> mockPrices = createMockPricesList();
        Prices expectedResult = createPrices(2L, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30), 2L, 35455L, 1L, 25.45, "EUR");

        Long brandId = requestDTO.getBrandId().orElse(1L);
        Long productId = requestDTO.getProductId().orElse(35455L);

        when(pricesRepository.findByStartDateBrandIdProductId(requestDTO.getRequestDate(), brandId, productId))
                .thenReturn(mockPrices.stream()
                        .filter(price -> Objects.equals(price.getProductId(), productId)
                                && Objects.equals(price.getBrandId(), brandId)
                                && requestDateBetweenStartDateEndDate(requestDTO.getRequestDate(), price.getStartDate(), price.getEndDate()))
                        .collect(Collectors.toList()));

        // Call the method
        PricesDTO result = pricesService.getPricesByStartDateProductIdBrandId(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult.getPriceId(), result.getPriceId());
    }

    @Test
    public void filterTest3() {
        // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // Mock request
        PricesRequestDTO requestDTO = getPricesRequestDTO(LocalDateTime.of(2020, 6, 14, 21, 0, 0), Optional.empty(), Optional.empty());

        // Mock data
        List<Prices> mockPrices = createMockPricesList();
        Prices expectedResult = createPrices(1L, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L, 35455L, 0L, 35.50, "EUR");

        Long brandId = requestDTO.getBrandId().orElse(1L);
        Long productId = requestDTO.getProductId().orElse(35455L);

        when(pricesRepository.findByStartDateBrandIdProductId(requestDTO.getRequestDate(), brandId, productId))
                .thenReturn(mockPrices.stream()
                        .filter(price -> Objects.equals(price.getProductId(), productId)
                                && Objects.equals(price.getBrandId(), brandId)
                                && requestDateBetweenStartDateEndDate(requestDTO.getRequestDate(), price.getStartDate(), price.getEndDate()))
                        .collect(Collectors.toList()));

        // Call the method
        PricesDTO result = pricesService.getPricesByStartDateProductIdBrandId(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult.getPriceId(), result.getPriceId());
    }

    @Test
    public void filterTest4() {
        // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        // Mock request
        PricesRequestDTO requestDTO = getPricesRequestDTO(LocalDateTime.of(2020, 6, 15, 10, 0, 0), Optional.empty(), Optional.empty());

        // Mock data
        List<Prices> mockPrices = createMockPricesList();
        Prices expectedResult = createPrices(3L, LocalDateTime.of(2020, 6, 15, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0), 3L, 35455L, 1L, 30.50, "EUR");

        Long brandId = requestDTO.getBrandId().orElse(1L);
        Long productId = requestDTO.getProductId().orElse(35455L);

        when(pricesRepository.findByStartDateBrandIdProductId(requestDTO.getRequestDate(), brandId, productId))
                .thenReturn(mockPrices.stream()
                        .filter(price -> Objects.equals(price.getProductId(), productId)
                                && Objects.equals(price.getBrandId(), brandId)
                                && requestDateBetweenStartDateEndDate(requestDTO.getRequestDate(), price.getStartDate(), price.getEndDate()))
                        .collect(Collectors.toList()));

        // Call the method
        PricesDTO result = pricesService.getPricesByStartDateProductIdBrandId(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult.getPriceId(), result.getPriceId());
    }

    @Test
    public void filterTest5() {
        // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        // Mock request
        PricesRequestDTO requestDTO = getPricesRequestDTO(LocalDateTime.of(2020, 6, 16, 21, 0, 0), Optional.empty(), Optional.empty());

        // Mock data
        List<Prices> mockPrices = createMockPricesList();
        Prices expectedResult = createPrices(4L, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4L, 35455L, 1L, 38.95, "EUR");

        Long brandId = requestDTO.getBrandId().orElse(1L);
        Long productId = requestDTO.getProductId().orElse(35455L);

        when(pricesRepository.findByStartDateBrandIdProductId(requestDTO.getRequestDate(), brandId, productId))
                .thenReturn(mockPrices.stream()
                        .filter(price -> Objects.equals(price.getProductId(), productId)
                                && Objects.equals(price.getBrandId(), brandId)
                                && requestDateBetweenStartDateEndDate(requestDTO.getRequestDate(), price.getStartDate(), price.getEndDate()))
                        .collect(Collectors.toList()));

        // Call the method
        PricesDTO result = pricesService.getPricesByStartDateProductIdBrandId(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResult.getPriceId(), result.getPriceId());
    }

    private PricesRequestDTO getPricesRequestDTO(LocalDateTime requestDate, Optional<Long> brandId, Optional<Long> productId) {
        PricesRequestDTO requestDTO = new PricesRequestDTO();
        requestDTO.setRequestDate(requestDate);
        requestDTO.setBrandId(brandId);
        requestDTO.setProductId(productId);
        return requestDTO;
    }

    private Prices createPrices(Long brandId, LocalDateTime startDate, LocalDateTime endDate, Long priceId, Long productId, Long priority, double price, String curr) {
        return new Prices(
                priceId,
                startDate,
                endDate,
                priority,
                price,
                curr,
                brandId,
                productId
        );
    }

    private List<Prices> createMockPricesList() {
        return Arrays.asList(
                createPrices(1L, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L, 35455L, 0L, 35.50, "EUR"),
                createPrices(1L, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30), 2L, 35455L, 1L, 25.45, "EUR"),
                createPrices(1L, LocalDateTime.of(2020, 6, 15, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0), 3L, 35455L, 1L, 30.50, "EUR"),
                createPrices(1L, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4L, 35455L, 1L, 38.95, "EUR")
        );
    }

    private boolean requestDateBetweenStartDateEndDate(LocalDateTime requestDate, LocalDateTime startDate, LocalDateTime endDate) {
        return requestDate.isAfter(startDate) && requestDate.isBefore(endDate);
    }
}
