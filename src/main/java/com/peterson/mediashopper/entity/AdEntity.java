package com.peterson.mediashopper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "AD")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AD_ID")
    private Integer adId;

    @Column(name = "DAY")
    private String day;

    @Column(name = "TIME_SLOT")
    private String timeSlot;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "BUNDLE_ID")
    private Integer bundleId;

    @Column(name = "TIER")
    private String tier;

    @Column(name = "TYPE")
    private String type;
}

