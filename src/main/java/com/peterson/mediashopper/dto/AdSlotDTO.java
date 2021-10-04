package com.peterson.mediashopper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdSlotDTO {
    private String day;
    private String timeSlot;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer bundleId;
    private String tier;
    private String type;
}

