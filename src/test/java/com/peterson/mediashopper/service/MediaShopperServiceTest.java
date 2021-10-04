package com.peterson.mediashopper.service;

import com.peterson.mediashopper.dto.AdBundleDTO;
import com.peterson.mediashopper.dto.AdSlotDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MediaShopperServiceTest {

    @Autowired
    MediaShopperService mediaShopperService;

    @Test
    void shouldReturnAllAdSlots() {
        // Given: a call to the service

        // When: the service method is called
        final List<AdSlotDTO> adSlotDTOs = mediaShopperService.retrieveAdTimeSlotDetails(null, null);

        // Then: all ad slots and bundles will be returned
        assertNotNull(adSlotDTOs);
        assertFalse(adSlotDTOs.isEmpty());
        assertEquals(5, adSlotDTOs.size());
    }

    @Test
    void shouldReturnAllAdSlotsForAGivenTier() {
        // Given: a tier
        final String tier = "PRIME";

        // When: the service method is called
        final List<AdSlotDTO> adSlotDTOs = mediaShopperService.retrieveAdTimeSlotDetails(null, tier);

        // Then: all ad slots and bundles will be returned
        assertNotNull(adSlotDTOs);
        assertFalse(adSlotDTOs.isEmpty());
        assertEquals(3, adSlotDTOs.size());
    }

    @Test
    void shouldReturnAllAdSlotsForAGivenDay() {
        // Given: a tier
        final String day = "THURSDAY";

        // When: the service method is called
        final List<AdSlotDTO> adSlotDTOs = mediaShopperService.retrieveAdTimeSlotDetails(day, null);

        // Then: all ad slots and bundles will be returned
        assertNotNull(adSlotDTOs);
        assertFalse(adSlotDTOs.isEmpty());
        assertEquals(2, adSlotDTOs.size());
    }

    @Test
    void shouldReturnAllAdSlotsForAGivenDayAndTier() {
        // Given: a tier
        final String day = "THURSDAY";
        final String tier = "PRIME";

        // When: the service method is called
        final List<AdSlotDTO> adSlotDTOs = mediaShopperService.retrieveAdTimeSlotDetails(day, tier);

        // Then: all ad slots and bundles will be returned
        assertNotNull(adSlotDTOs);
        assertFalse(adSlotDTOs.isEmpty());
        assertEquals(1, adSlotDTOs.size());
        assertEquals(BigDecimal.valueOf(300.00).setScale(2, RoundingMode.HALF_UP),
                adSlotDTOs.get(0).getPrice());
        assertEquals(BigDecimal.valueOf(120.00).setScale(2, RoundingMode.HALF_UP),
                adSlotDTOs.get(0).getCost());
    }

    @Test
    void shouldReturnAllAdBundles() {
        // Given: a call to the service

        // When: the service method is called
        final List<AdBundleDTO> adBundleDTOs = mediaShopperService.retrieveAdBundleDetails();

        // Then: the bundles will be returned
        assertNotNull(adBundleDTOs);
        assertFalse(adBundleDTOs.isEmpty());
        assertEquals(2, adBundleDTOs.size());

        // And: the add bundles will contain all the ad details:
        assertThat(adBundleDTOs, containsInAnyOrder(
                hasProperty("adSlots", hasSize(3)),
                hasProperty("adSlots", hasSize(2))
        ));
    }
}
