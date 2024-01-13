package com.bcnc.inditex.infrastructure.repository;

import com.bcnc.inditex.domain.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query("SELECT pr " +
            "FROM Prices pr " +
            "WHERE pr.brandId = :brandId " +
            "   AND pr.productId = :productId " +
            "   AND :requestDate BETWEEN pr.startDate AND pr.endDate " +
            "ORDER BY pr.priority DESC, pr.price DESC " +
            "LIMIT 1")
    Prices findByRequestDateBrandIdProductId(
            @Param("requestDate") LocalDateTime requestDate,
            @Param("brandId") Long brandId,
            @Param("productId") Long productId
    );

}
