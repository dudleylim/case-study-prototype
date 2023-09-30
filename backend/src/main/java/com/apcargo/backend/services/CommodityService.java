package com.apcargo.backend.services;

import com.apcargo.backend.exceptions.FieldException;
import com.apcargo.backend.models.*;
import com.apcargo.backend.repositories.ActivityLogRepository;
import com.apcargo.backend.repositories.CommodityRepository;
import com.apcargo.backend.requests.CommodityAddRequest;
import com.apcargo.backend.requests.CommodityUpdateRequest;
import com.apcargo.backend.utils.CommodityFieldChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommodityService {
    private final CommodityRepository commodityRepository;
    private final CommodityClassService commodityClassService;
    private final ActivityLogRepository activityLogRepository;

    public List<Commodity> getCommodities() {
        return commodityRepository.findAll();
    }

    public Commodity getCommodity(Long id) throws ResponseStatusException {
        return commodityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Commodity not found"));
    }

    public void createCommodity(CommodityAddRequest commodityAddRequest) throws FieldException, ResponseStatusException {
        CommodityFieldChecker.checkFieldsAdd(commodityAddRequest);

        if (commodityRepository.findFirstByCommName(commodityAddRequest.getCommName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Commodity Class name already exists. Please enter a different name");
        }

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDateTime = formatter.format(dateTime);

        Long commClassId = Long.parseLong(String.valueOf(commodityAddRequest.getCommClassId()));
        CommodityClass commodityClass = commodityClassService.getCommodityClass(commClassId);

        Commodity newCommodity = Commodity.builder()
                .commName(commodityAddRequest.getCommName())
                .commDescription(commodityAddRequest.getCommDescription())
                .commClass(commodityClass)
                .commStatus(Status.ACTIVE)
                .createdBy("admin")
                .lastModifiedBy("admin")
                .dateCreated(formattedDateTime)
                .dateLastModified(formattedDateTime)
                .build();

        commodityRepository.save(newCommodity);

        // Activity Log
        ActivityLog newActivity = ActivityLog.builder()
                .activity(Activity.COMMODITY_CREATED)
                .activityOwner("admin")
                .activityDateTime(formattedDateTime)
                .build();

        activityLogRepository.save(newActivity);
    }

    public void updateCommodity(Long id, CommodityUpdateRequest commodityUpdateRequest) throws FieldException, ResponseStatusException {
        CommodityFieldChecker.checkFieldsUpdate(commodityUpdateRequest);

        Commodity commodityToBeUpdated = commodityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No records match the search or filter criteria."));

        Optional<Commodity> isCommodityExist = commodityRepository.findFirstByCommName(commodityUpdateRequest.getCommName());
        if (isCommodityExist.isPresent() && !Objects.equals(isCommodityExist.get().getCommId(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Commodity Class name already exists. Please enter a different name");
        }

        Long commClassId = Long.parseLong(String.valueOf(commodityUpdateRequest.getCommClassId()));
        CommodityClass commodityClass = commodityClassService.getCommodityClass(commClassId);

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDateTime = formatter.format(dateTime);

        // Updating Values
        commodityToBeUpdated.setCommName(commodityUpdateRequest.getCommName());
        commodityToBeUpdated.setCommDescription(commodityUpdateRequest.getCommDescription());
        commodityToBeUpdated.setCommClass(commodityClass);
        commodityToBeUpdated.setCommStatus(Status.valueOf(commodityUpdateRequest.getCommStatus()));
        commodityToBeUpdated.setCommStatusReason(commodityUpdateRequest.getCommStatusReason());

        // Updating System Generated Values
        commodityToBeUpdated.setLastModifiedBy("admin");
        commodityToBeUpdated.setDateLastModified(formattedDateTime);

        commodityRepository.save(commodityToBeUpdated);

        // Insert Activity Log
        ActivityLog newActivity = ActivityLog.builder()
                .activity(Activity.COMMODITY_UPDATED)
                .activityOwner("admin")
                .activityDateTime(formattedDateTime)
                .build();

        activityLogRepository.save(newActivity);
    }
}
