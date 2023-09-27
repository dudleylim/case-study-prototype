package com.apcargo.backend.repositories;

import com.apcargo.backend.models.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {
}
