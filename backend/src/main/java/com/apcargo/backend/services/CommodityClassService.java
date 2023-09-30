package com.apcargo.backend.services;

import com.apcargo.backend.exceptions.FieldException;
import com.apcargo.backend.models.Activity;
import com.apcargo.backend.models.ActivityLog;
import com.apcargo.backend.models.CommodityClass;
import com.apcargo.backend.models.Status;
import com.apcargo.backend.repositories.ActivityLogRepository;
import com.apcargo.backend.repositories.CommodityClassRepository;
import com.apcargo.backend.requests.CommodityClassAddRequest;
import com.apcargo.backend.requests.CommodityClassUpdateRequest;
import com.apcargo.backend.utils.CommodityClassFieldChecker;
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
public class CommodityClassService {
    private final CommodityClassRepository commodityClassRepository;
    private final ActivityLogRepository activityLogRepository;

    public List<CommodityClass> getCommodityClasses() {
        return commodityClassRepository.findAll();
    }

    public CommodityClass getCommodityClass(Long id) throws ResponseStatusException {
        return commodityClassRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No records match the search or filter criteria."));
    }

    public void addCommodityClass(CommodityClassAddRequest commodityClassAddRequest) throws ResponseStatusException, FieldException {
        CommodityClassFieldChecker.checkFieldsAdd(commodityClassAddRequest);

        if (commodityClassRepository.findFirstByCommClassName(commodityClassAddRequest.getCommClassName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Commodity Class name already exists. Please enter a different name");
        }

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDateTime = formatter.format(dateTime);

        CommodityClass newCommodityClass = CommodityClass.builder()
                .commClassName(commodityClassAddRequest.getCommClassName())
                .commClassDescription(commodityClassAddRequest.getCommClassDescription())
                .commClassStatus(Status.ACTIVE)
                .serviceType(commodityClassAddRequest.getServiceType())
                .createdBy("admin")
                .lastModifiedBy("admin")
                .dateCreated(formattedDateTime)
                .dateLastModified(formattedDateTime)
                .build();

        commodityClassRepository.save(newCommodityClass);

        // Insert addition of activity log here
        ActivityLog newActivity = ActivityLog.builder()
                .activity(Activity.COMMODITY_CLASS_CREATED)
                .activityOwner("admin")
                .activityDateTime(formattedDateTime)
                .build();

        activityLogRepository.save(newActivity);
    }

    public void updateCommodityClass(Long id, CommodityClassUpdateRequest commodityClassUpdateRequest) throws ResponseStatusException, FieldException {
        CommodityClassFieldChecker.checkFieldsUpdate(commodityClassUpdateRequest);

        CommodityClass commodityClassToBeUpdated =  commodityClassRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No records match the search or filter criteria."));

        Optional<CommodityClass> isCommodityClassExist = commodityClassRepository.findFirstByCommClassName(commodityClassUpdateRequest.getCommClassName());
        if (isCommodityClassExist.isPresent() && !Objects.equals(isCommodityClassExist.get().getCommClassId(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Commodity Class name already exists. Please enter a different name");
        }

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDateTime = formatter.format(dateTime);

        commodityClassToBeUpdated.setCommClassName(commodityClassUpdateRequest.getCommClassName());
        commodityClassToBeUpdated.setCommClassDescription(commodityClassUpdateRequest.getCommClassDescription());
        commodityClassToBeUpdated.setCommClassStatus(Status.valueOf(commodityClassUpdateRequest.getCommClassStatus()));
        commodityClassToBeUpdated.setCommClassStatusReason(commodityClassUpdateRequest.getCommClassStatusReason());
        commodityClassToBeUpdated.setServiceType(commodityClassUpdateRequest.getServiceType());
        commodityClassToBeUpdated.setLastModifiedBy("admin");
        commodityClassToBeUpdated.setDateLastModified(formattedDateTime);

        commodityClassRepository.save(commodityClassToBeUpdated);

        // Insert addition of activity log here
        ActivityLog newActivity = ActivityLog.builder()
                .activity(Activity.COMMODITY_CLASS_UPDATED)
                .activityOwner("admin")
                .activityDateTime(formattedDateTime)
                .build();

        activityLogRepository.save(newActivity);
    }
}




















