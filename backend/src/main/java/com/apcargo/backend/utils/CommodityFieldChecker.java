package com.apcargo.backend.utils;

import com.apcargo.backend.exceptions.Error;
import com.apcargo.backend.exceptions.FieldException;
import com.apcargo.backend.models.Status;
import com.apcargo.backend.requests.CommodityAddRequest;
import com.apcargo.backend.requests.CommodityUpdateRequest;

import java.util.ArrayList;
import java.util.List;

public class CommodityFieldChecker {
    public static void checkFieldsAdd(CommodityAddRequest commodityAddRequest) throws FieldException {
        List<String> messages = new ArrayList<>();

        // Error Checking
        try {
            if (commodityAddRequest.getCommName().isBlank() || commodityAddRequest.getCommName().isEmpty()) {
                messages.add("Commodity name is required. Please enter a valid value.");
            }
        } catch (NullPointerException e) {
            messages.add("Commodity name is required. Please enter a valid value.");
        }

        try {
            Long commClassId = Long.parseLong(String.valueOf(commodityAddRequest.getCommClassId()));
        } catch (NullPointerException e) {
            messages.add("Commodity class is required. Please enter a valid value.");
        } catch (NumberFormatException e) {
            messages.add("Commodity class id should be an integer.");
        }

        if (!messages.isEmpty()) {
            throw new FieldException(Error.MISSING_REQUIRED_FIELDS, "Missing fields", messages);
        }
    }
    public static void checkFieldsUpdate(CommodityUpdateRequest commodityUpdateRequest) throws FieldException {
        List<String> messages = new ArrayList<>();

        // Error Checking
        try {
            if (commodityUpdateRequest.getCommName().isBlank() || commodityUpdateRequest.getCommName().isEmpty()) {
                messages.add("Commodity name is required. Please enter a valid value.");
            }
        } catch (NullPointerException e) {
            messages.add("Commodity name is required. Please enter a valid value.");
        }

        try {
            Long commClassId = Long.parseLong(String.valueOf(commodityUpdateRequest.getCommClassId()));
        } catch (NullPointerException e) {
            messages.add("Commodity class is required. Please enter a valid value.");
        } catch (NumberFormatException e) {
            messages.add("Commodity class id should be an integer.");
        }

        try {
            if (commodityUpdateRequest.getCommStatus() == null) {
                messages.add("Commodity status is required. Please enter a valid value.");
            } else {
                Status status = Status.valueOf(commodityUpdateRequest.getCommStatus());
                if (status.equals(Status.INACTIVE)) {
                    try {
                        if (commodityUpdateRequest.getCommStatusReason().isEmpty() || commodityUpdateRequest.getCommStatusReason().isBlank()) {
                            messages.add("Commodity reason is required if Commodity is inactive. Please enter a valid value.");
                        }
                    } catch (NullPointerException e) {
                        messages.add("Commodity status reason is required if status is inactive. Please enter a valid value.");
                    }
                }
            }
        } catch (NullPointerException e) {
            messages.add("Commodity status is required. Please enter a valid value.");
        } catch (IllegalArgumentException e) {
            messages.add("Commodity status should be ACTIVE or INACTIVE.");
        }

        if (!messages.isEmpty()) {
            throw new FieldException(Error.MISSING_REQUIRED_FIELDS, "Missing fields", messages);
        }
    }
}
