package com.apcargo.backend.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityClassAddRequest {
    @NotNull
    private String serviceType;

    @NotNull
    private String commClassName;
    private String commClassDescription;
}
