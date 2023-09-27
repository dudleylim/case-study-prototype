package com.apcargo.backend.services;

import com.apcargo.backend.models.Commodity;
import com.apcargo.backend.models.Status;
import com.apcargo.backend.repositories.CommodityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommodityService {
    private final CommodityRepository commodityRepository;

    public List<Commodity> getCommodities() {
        return commodityRepository.findAll();
    }

    public Commodity getCommodity(Long id) throws ResponseStatusException {
        return commodityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Commodity not found"));
    }

    public void createCommodity(Commodity commodity) {
        // At the moment, commodity only has name and probably description

        // We still need to set the ff.
        //// status
        //// created_by
        //// last_modified_by
        //// date_created
        //// date_last_modified
        commodity.setComm_status(Status.ACTIVE);
        commodity.setCreated_by("Dude");
        commodity.setLast_modified_by("Dude");

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

        commodity.setDate_created(dateTimeFormatter.format(dateTime));
        commodity.setLast_modified_by(dateTimeFormatter.format(dateTime));
    }

    public void updateCommodity(Commodity updatedCommodity) throws ResponseStatusException {
        Commodity commodity = commodityRepository.findById(updatedCommodity.getComm_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Commodity not found"));
    }
}
