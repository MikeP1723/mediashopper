package com.peterson.mediashopper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "AD_BUNDLE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdBundleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUNDLE_ID")
    private Integer bundleId;

    @Column(name = "AD_IDS")
    private String adIds;

    @Column(name = "BUNDLE_PRICE")
    private BigDecimal bundlePrice;

    @Column(name = "TIER")
    private String tier;
}
