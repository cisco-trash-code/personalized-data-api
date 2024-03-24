package com.shop.controller;

import com.shop.dto.ShopperRequest;
import com.shop.dto.ShopperResponse;
import com.shop.service.PersonalizedDataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API: Personalized Data API
 * Date: 2024-03-24
 * Person: sovisrushain
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalized-data")
public class PersonalizedDataController {

    private final PersonalizedDataService personalizedDataService;

    private static final Logger logger = LoggerFactory.getLogger(PersonalizedDataController.class);

    @PostMapping("/")
    public ResponseEntity<ShopperResponse> getPersonalizedData(@RequestBody ShopperRequest request,
                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("PersonalizedDataController: getPersonalizedData(): accessed");
        ShopperResponse data = personalizedDataService.getPersonalizedData(request, page, size);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
