package com.peterson.mediashopper.repository;

import com.peterson.mediashopper.entity.AdBundleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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

        // When: the repository method is called

        // Then: the correct ad bundle will be returned
    }

    @Test
    void shouldInsertANewAdBundle() {
        // Given: a valid ad bundle entity

        // When: the repository save method is called

        // Then: the ad bundle will be inserted
    }
}
