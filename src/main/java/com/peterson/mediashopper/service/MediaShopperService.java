package com.peterson.mediashopper.service;

import com.peterson.mediashopper.dto.AdBundleDTO;
import com.peterson.mediashopper.dto.AdSlotDTO;
import com.peterson.mediashopper.entity.AdBundleEntity;
import com.peterson.mediashopper.entity.AdEntity;
import com.peterson.mediashopper.repository.AdBundleRepository;
import com.peterson.mediashopper.repository.AdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class MediaShopperService {

    private final transient AdRepository adRepository;
    private final transient AdBundleRepository adBundleRepository;

    @Autowired
    public MediaShopperService(final AdRepository adRepository, final AdBundleRepository adBundleRepository) {
        this.adRepository = adRepository;
        this.adBundleRepository = adBundleRepository;
    }

    public List<AdSlotDTO> retrieveAdTimeSlotDetails(final String day, final String tier) {

        final List<AdSlotDTO> adSlotDTOs = new ArrayList<>();

        List<AdEntity> adEntities;

        if (null != day && null != tier) {
            log.info("Finding AD slots for day {} and tier {}", day, tier);
            adEntities = adRepository.findAdEntitiesByDayAndTier(day, tier);
        } else if (null == day && null != tier) {
            log.info("Finding AD slots for tier {}", tier);
            adEntities = adRepository.findAdEntitiesByTier(tier);
        } else if (null != day) {
            log.info("Finding Ad slots for day {}", day);
            adEntities = adRepository.findAdEntitiesByDay(day);
        } else {
            log.info("Finding all Ad slots");
            adEntities = adRepository.findAll();
        }

        adEntities.forEach(adEntity -> {
            final AdSlotDTO adSlotDTO = AdSlotDTO.builder()
                    .day(adEntity.getDay())
                    .timeSlot(adEntity.getTimeSlot())
                    .cost(adEntity.getCost().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))
                    .price(adEntity.getPrice().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))
                    .bundleId(adEntity.getBundleId())
                    .tier(adEntity.getTier())
                    .type(adEntity.getType())
                    .build();
            adSlotDTOs.add(adSlotDTO);
        });

        return adSlotDTOs;
    }

    public List<AdBundleDTO> retrieveAdBundleDetails() {
        final List<AdBundleEntity> adBundleEntities = adBundleRepository.findAll();

        final List<AdBundleDTO> adBundleDTOs = new ArrayList<>();

        adBundleEntities.forEach(adBundleEntity -> {

            List<Integer> adIds = Arrays.stream(adBundleEntity.getAdIds().split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt).boxed().toList();

            final List<AdEntity> adEntities = adRepository.findAdEntitiesByAdIdIn(adIds);

            List<AdSlotDTO> adSlots = new ArrayList<>();
            adEntities.forEach(adEntity -> {
                final AdSlotDTO adSlotDTO = AdSlotDTO.builder()
                        .day(adEntity.getDay())
                        .timeSlot(adEntity.getTimeSlot())
                        .bundleId(adEntity.getBundleId())
                        .price(adEntity.getPrice())
                        .cost(adEntity.getCost())
                        .type(adEntity.getType())
                        .tier(adEntity.getTier())
                        .build();
                adSlots.add(adSlotDTO);
            });

            final AdBundleDTO adBundleDTO = AdBundleDTO.builder()
                    .bundleId(adBundleEntity.getBundleId())
                    .bundlePrice(adBundleEntity.getBundlePrice().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))
                    .tier(adBundleEntity.getTier())
                    .adSlots(adSlots)
                    .build();

            adBundleDTOs.add(adBundleDTO);
        });

        return adBundleDTOs;
    }
}
