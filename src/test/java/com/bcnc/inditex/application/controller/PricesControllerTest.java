package com.bcnc.inditex.application.controller;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.application.mapper.PricesMapper;
import com.bcnc.inditex.application.service.PricesService;
import com.bcnc.inditex.domain.Prices;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PricesControllerTest {

    @Mock
    private PricesService pricesService;

    @InjectMocks
    private PricesController pricesController;

    private MockMvc mockMvc;
    private PricesMapper pricesMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        pricesMapper = Mappers.getMapper(PricesMapper.class);
        this.mockMvc = standaloneSetup(pricesController).build();
    }

    @Test
    public void testGetAll() {
        // Mocking the service response
        List<PricesDTO> pricesList = createMockPricesList().stream()
                .map(pricesMapper::entityToDTO)
                .collect(Collectors.toList());
        when(pricesService.getAll()).thenReturn(pricesList);

        // Performing the test
        IntStream.range(0, pricesList.size())
                        .forEach(i -> {
                            PricesDTO pricesDTO = pricesList.get(i);

                            try {
                                mockMvc.perform(MockMvcRequestBuilders.get("/prices"))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(MockMvcResultMatchers.jsonPath("$["+i+"].priceId").value(pricesDTO.getPriceId()));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
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

}
