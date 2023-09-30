package com.apcargo.backend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityUpdateRequest {
    private String commName;
    private String commDescription;
    private Object commClassId;
    private String commStatus;
    private String commStatusReason;
}
