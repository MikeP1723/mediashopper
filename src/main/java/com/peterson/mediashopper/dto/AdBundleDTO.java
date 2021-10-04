package com.peterson.mediashopper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdBundleDTO {
    private Integer bundleId;
    private BigDecimal bundlePrice;
    private String tier;
    private List<AdSlotDTO> adSlots;
}
