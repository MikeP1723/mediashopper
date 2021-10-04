package com.peterson.mediashopper.controller;

import com.peterson.mediashopper.dto.AdBundleDTO;
import com.peterson.mediashopper.dto.AdSlotDTO;
import com.peterson.mediashopper.service.MediaShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class MediaShopperController {

    private final transient MediaShopperService mediaShopperService;

    @Autowired
    public MediaShopperController(final MediaShopperService mediaShopperService) {
        this.mediaShopperService = mediaShopperService;
    }

    @GetMapping(path = "/adSlots")
    final List<AdSlotDTO> retrieveAdSlots(@Nullable @RequestParam(name = "day") final String day,
                                          @Nullable @RequestParam(name = "tier") final String tier) {
        return mediaShopperService.retrieveAdTimeSlotDetails(day, tier);
    }

    @GetMapping(path = "/adBundles")
    final List<AdBundleDTO> retrieveAdBundles() {
        return mediaShopperService.retrieveAdBundleDetails();
    }
}
