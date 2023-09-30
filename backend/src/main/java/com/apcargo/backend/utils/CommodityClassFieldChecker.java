package com.apcargo.backend.utils;

import com.apcargo.backend.exceptions.Error;
import com.apcargo.backend.exceptions.FieldException;
import com.apcargo.backend.models.Status;
import com.apcargo.backend.requests.CommodityClassAddRequest;
import com.apcargo.backend.requests.CommodityClassUpdateRequest;

import java.util.ArrayList;
import java.util.List;

public class CommodityClassFieldChecker {
    public static void checkFieldsAdd(CommodityClassAddRequest commodityClassToBeAdded) throws FieldException {
        List<String> messages = new ArrayList<>();

        // Error Checking
        try {
            if (commodityClassToBeAdded.getServiceType().isBlank() || commodityClassToBeAdded.getServiceType().isEmpty()) {
                messages.add("Commodity Class service type is required. Please enter a valid value.");
            }
        } catch (NullPointerException e) {
            messages.add("Commodity Class service type is required. Please enter a valid value.");
        }

        try {
            if (commodityClassToBeAdded.getCommClassName().isBlank() || commodityClassToBeAdded.getCommClassName().isEmpty()) {
                messages.add("Commodity Class name is required. Please enter a valid value.");
            }
        } catch (NullPointerException e) {
            messages.add("Commodity Class name is required. Please enter a valid value.");
        }

        if (!messages.isEmpty()) {
            throw new FieldException(Error.MISSING_REQUIRED_FIELDS, "Missing fields", messages);
        }
//        throw new FieldException(Error.INVALID_FIELD_TYPE, "");
    }

    public static void checkFieldsUpdate(CommodityClassUpdateRequest commodityClassToBeUpdated) throws FieldException {
        List<String> messages = new ArrayList<>();

        // Error Checking
        try {
            if (commodityClassToBeUpdated.getServiceType().isEmpty() || commodityClassToBeUpdated.getServiceType().isBlank()) {
                messages.add("Commodity Class service type is required. Please enter a valid value.");
            }
        } catch (NullPointerException e) {
            messages.add("Commodity Class service type is required. Please enter a valid value.");
        }


        try {
            if (commodityClassToBeUpdated.getCommClassName().isEmpty() || commodityClassToBeUpdated.getCommClassName().isBlank()) {
                messages.add("Commodity Class name is required. Please enter a valid value.");
            }
        } catch (NullPointerException e) {
            messages.add("Commodity Class name is required. Please enter a valid value.");
        }

        try {
            if (commodityClassToBeUpdated.getCommClassStatus() == null) {
                messages.add("Commodity Class status is required. Please enter a valid value.");
            } else {
                Status status = Status.valueOf(commodityClassToBeUpdated.getCommClassStatus());
                if (status.equals(Status.INACTIVE)) {
                    try {
                        if (commodityClassToBeUpdated.getCommClassStatusReason().isEmpty() || commodityClassToBeUpdated.getCommClassStatusReason().isBlank()) {
                            messages.add("Commodity Class reason is required if Commodity Class is inactive. Please enter a valid value.");
                        }
                    } catch (NullPointerException e) {
                        messages.add("Commodity Class status reason is required if status is inactive. Please enter a valid value.");
                    }
                }
            }
        } catch (NullPointerException e) {
            messages.add("Commodity Class status is required. Please enter a valid value.");
        } catch (IllegalArgumentException e) {
            messages.add("Commodity Class status should be ACTIVE or INACTIVE.");
        }

        if (!messages.isEmpty()) {
            throw new FieldException(Error.MISSING_REQUIRED_FIELDS, "Missing fields", messages);
        }
    }
}
