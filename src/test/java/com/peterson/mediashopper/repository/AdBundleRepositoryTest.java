package com.peterson.mediashopper.repository;

import com.peterson.mediashopper.entity.AdBundleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AdBundleRepositoryTest {

    @Autowired
    private AdBundleRepository adBundleRepository;

    @Test
    void shouldRetrieveAllAddBundles() {
        // When: the repository method is called
        final List<AdBundleEntity> adBundles = adBundleRepository.findAll();

        // Then: the ad bundles will be returned
        assertFalse(adBundles.isEmpty());
        assertEquals(2, adBundles.size());
    }

    @Test
    void shouldRetrieveAnAdBundleById() {
        // Given: a valid ad bundle ID
        final Integer bundleId = 1;

        // When: the repository method is called
        final Optional<AdBundleEntity> adBundleEntityOptional = adBundleRepository.findById(bundleId);

        // Then: the correct ad bundle will be
        assertTrue(adBundleEntityOptional.isPresent());
        assertNotNull(adBundleEntityOptional.get());
    }

    @Test
    void shouldInsertANewAdBundle() {
        // Given: a valid ad bundle entity
        final AdBundleEntity adBundleEntity = AdBundleEntity.builder()
                .adIds("4,5")
                .bundlePrice(new BigDecimal(150000))
                .tier("MIX")
                .build();

        // When: the repository save method is called
        final AdBundleEntity persistedAdBundleEntity = adBundleRepository.save(adBundleEntity);

        // Then: the ad bundle will be inserted
        assertEquals(3, persistedAdBundleEntity.getBundleId());
    }
}
