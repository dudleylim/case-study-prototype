package com.apcargo.backend.repositories;

import com.apcargo.backend.models.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    Optional<Commodity> findFirstByCommName(String name);
}
