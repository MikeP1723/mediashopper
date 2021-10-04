package com.peterson.mediashopper.controller;

import com.peterson.mediashopper.dto.AdBundleDTO;
import com.peterson.mediashopper.dto.AdSlotDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
public class MediaShopperControllerTest {

    @Autowired
    private MediaShopperController mediaShopperController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllAdSlots() {
        // Given: a request with no parameters

        // When: the controller method is called
        final List<AdSlotDTO> adSlots = mediaShopperController.retrieveAdSlots(null, null);

        // Then: the response will be returned
        assertNotNull(adSlots);
        assertFalse(adSlots.isEmpty());
        assertEquals(5, adSlots.size());
    }

    @Test
    void shouldReturnAdSlotsForAGivenDay() {
        // Given: a request with a specified day
        final String day = "THURSDAY";

        // When: the controller method is called
        final List<AdSlotDTO> adSlots = mediaShopperController.retrieveAdSlots(day, null);

        // Then: the response will be returned
        assertNotNull(adSlots);
        assertFalse(adSlots.isEmpty());
        assertEquals(2, adSlots.size());
    }

    @Test
    void shouldReturnAdSlotsForAGivenTier() {
        // Given: a request with a specified day
        final String tier = "PRIME";

        // When: the controller method is called
        final List<AdSlotDTO> adSlots = mediaShopperController.retrieveAdSlots(null, tier);

        // Then: the response will be returned
        assertNotNull(adSlots);
        assertFalse(adSlots.isEmpty());
        assertEquals(3, adSlots.size());
    }

    @Test
    void shouldReturnAdSlotsForAGivenDayAndTier() {
        // Given: a request with a specified day
        final String day = "THURSDAY";
        final String tier = "PRIME";

        // When: the controller method is called
        final List<AdSlotDTO> adSlots = mediaShopperController.retrieveAdSlots(day, tier);

        // Then: the response will be returned
        assertNotNull(adSlots);
        assertFalse(adSlots.isEmpty());
        assertEquals(1, adSlots.size());
    }

    @Test
    void shouldReturnAllAdBundles() {
        // Given: a request to retrieve ad bundles

        // When: the controller method is called
        final List<AdBundleDTO> dBundles = mediaShopperController.retrieveAdBundles();

        // Then: the response will be returned
        assertNotNull(dBundles);
        assertFalse(dBundles.isEmpty());
        assertEquals(2, dBundles.size());
    }

    @Test
    void webInterfaceForAdSlotsShouldWork() throws Exception {
        this.mockMvc.perform(get("/adSlots")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void webInterfaceForAdBundlesShouldWork() throws Exception {
        this.mockMvc.perform(get("/adBundles")).andDo(print()).andExpect(status().isOk());
    }
}
