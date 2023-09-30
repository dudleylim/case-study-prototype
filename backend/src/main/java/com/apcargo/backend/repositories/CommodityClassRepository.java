package com.apcargo.backend.repositories;

import com.apcargo.backend.models.CommodityClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommodityClassRepository extends JpaRepository<CommodityClass, Long> {
    Optional<CommodityClass> findFirstByCommClassName(String name);
}
