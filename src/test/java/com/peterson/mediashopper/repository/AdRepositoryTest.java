package com.peterson.mediashopper.repository;

import com.peterson.mediashopper.entity.AdEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AdRepositoryTest {

    @Autowired
    private AdRepository adRepository;

    @Test
    void shouldRetrieveAllAds() {
        // When: the repository is called
        final List<AdEntity> ads = adRepository.findAll();

        // Then: the results will be returned
        assertFalse(ads.isEmpty());
        assertEquals(5, ads.size());
    }

    @Test
    void shouldRetrieveAnAddById() {
        // When: the repository is called
        final Optional<AdEntity> ad = adRepository.findById(2);

        // Then: the results will be returned
        assertTrue(ad.isPresent());
        assertEquals("PRIME", ad.get().getTier());
        assertEquals("OTA", ad.get().getType());
    }

    @Test
    void shouldInsertAnAd() {
        // Given: a valid ad entity
        final AdEntity newAdEntity = AdEntity.builder()
                .day("THURSDAY")
                .timeSlot("2:00-5:00")
                .cost(new BigDecimal(10000))
                .price(new BigDecimal(12000))
                .bundleId(null)
                .tier("SUB-PRIME")
                .type("OTA")
                .build();

        // When: the repository save is called
        final AdEntity persistedAd = adRepository.save(newAdEntity);

        // Then: the add will be inserted
        assertNotNull(persistedAd);
        assertEquals(6, persistedAd.getAdId());
    }

    @Test
    void shouldRetrieveOnlyPrimeAds() {
        // Given: a specific tier
        final String adTier = "PRIME";

        // When: the repository method is called
        final List<AdEntity> ads = adRepository.findAdEntitiesByTier(adTier);

        // Then: the correct ad slots will be returned
        assertFalse(ads.isEmpty());
        assertThat(ads, containsInAnyOrder(
                hasProperty("adId", is(1)),
                hasProperty("adId", is(2)),
                hasProperty("adId", is(5))
        ));
    }

    @Test
    void shouldReturnAdsForAGivenDay() {
        // Given: a day
        final String adDay = "THURSDAY";

        // When: the repository method is called
        final List<AdEntity> ads = adRepository.findAdEntitiesByDay(adDay);

        // Then: the applicable ad slots will be returned
        assertFalse(ads.isEmpty());
        assertEquals(2, ads.size());

    }

    @Test
    void shouldReturnAdsForAGivenDayAndTier() {
        // Given: a day and a tier
        final String adDay = "THURSDAY";
        final String adTier = "PRIME";

        // When: the repository method is called
        final List<AdEntity> adEntities = adRepository.findAdEntitiesByDayAndTier(adDay, adTier);

        // Then: the applicable ad slots will be returned
        assertFalse(adEntities.isEmpty());
        assertEquals(1, adEntities.size());
    }

    @Test
    void shouldReturnAllAdSlotsForAListOfAdIds() {
        // Given: a list of ad ids
        final Integer[] adIds = {1,2,5};

        // When: the repository method is called
        final List<AdEntity> adEntities = adRepository.findAdEntitiesByAdIdIn(Arrays.asList(adIds));

        // Then: the applicable ad slots will be returned
        assertFalse(adEntities.isEmpty());
        assertEquals(3, adEntities.size());
    }
}
