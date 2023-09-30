package com.apcargo.backend.requests;

import com.apcargo.backend.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityClassUpdateRequest {
    private String serviceType;
    private String commClassName;
    private String commClassDescription;
    private String commClassStatus;
    private String commClassStatusReason;
}
