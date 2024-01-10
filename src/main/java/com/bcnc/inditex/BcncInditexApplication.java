package com.bcnc.inditex;

import com.bcnc.inditex.domain.Brand;
import com.bcnc.inditex.domain.Prices;
import com.bcnc.inditex.domain.Product;
import com.bcnc.inditex.infrastructure.repository.BrandRepository;
import com.bcnc.inditex.infrastructure.repository.PricesRepository;
import com.bcnc.inditex.infrastructure.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BcncInditexApplication {

    @Autowired
    private PricesRepository pricesRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(BcncInditexApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BcncInditexApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            log.info("BRAND");
            log.info("Preloading " + brandRepository.save(new Brand(1L, "Zara")));

            log.info("PRODUCT");
            log.info("Preloading " + productRepository.save(new Product(35455L)));

            log.info("PRICES");
            log.info("Preloading " + pricesRepository.save(new Prices(1L, LocalDateTime.of(2020, 06, 14, 00, 00, 00), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 0L, 35.50, "EUR", 1L, 35455L)));
            log.info("Preloading " + pricesRepository.save(new Prices(2L, LocalDateTime.of(2020, 06, 14, 15, 00, 00), LocalDateTime.of(2020, 06, 14, 18, 30, 00), 1L, 25.45, "EUR", 1L, 35455L)));
            log.info("Preloading " + pricesRepository.save(new Prices(3L, LocalDateTime.of(2020, 06, 15, 00, 00, 00), LocalDateTime.of(2020, 06, 15, 11, 00, 00), 1L, 30.50, "EUR", 1L, 35455L)));
            log.info("Preloading " + pricesRepository.save(new Prices(4L, LocalDateTime.of(2020, 06, 15, 16, 00, 00), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L, 38.95, "EUR", 1L, 35455L)));
        };
    }

}
