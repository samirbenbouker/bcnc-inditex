package com.bcnc.inditex.application.service.impl;

import com.bcnc.inditex.application.DTO.PricesDTO;
import com.bcnc.inditex.application.mapper.PricesMapper;
import com.bcnc.inditex.application.request.PricesRequestDTO;
import com.bcnc.inditex.application.service.PricesService;
import com.bcnc.inditex.domain.Prices;
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

        // Make a query for get prices with request parameters
        List<Prices> prices = pricesRepository.findByStartDateBrandIdProductId(pricesRequestDTO.getRequestDate(), brandId, productId);
        return pricesMapper.entityToDTO(getPriorityPrices(prices));
    }

    private Prices getPriorityPrices(List<Prices> prices) {
        if(prices.size() == 0) return null; // Return null if prices length its 0
        else if(prices.size() == 1) return prices.get(0); // If prices list will be 1 return that value don't needed to get a priority prices

        // Else prices list will have more than value, need filter prices by priority
        var selectedPrice = prices.get(0);
        for(var price: prices) {
            if (price.getPriority() > selectedPrice.getPriority() ||
                    (price.getPriority().equals(selectedPrice.getPriority())
                            && price.getPrice() > selectedPrice.getPrice())) {
                selectedPrice = price;
            }
        }

        return selectedPrice;
    }
}
