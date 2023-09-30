package com.apcargo.backend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityAddRequest {
    private String commName;
    private String commDescription;
    private Object commClassId;
}
