package com.bcnc.inditex.infrastructure.repository;

import com.bcnc.inditex.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
