package com.shop.controller;

import com.shop.dto.ShopperRequest;
import com.shop.dto.ShopperResponse;
import com.shop.service.PersonalizedDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalized-data")
public class PersonalizedDataController {

    private final PersonalizedDataService personalizedDataService;

    @PostMapping("/")
    public ResponseEntity<ShopperResponse> getPersonalizedData(@RequestBody ShopperRequest request) {
        ShopperResponse data = personalizedDataService.getPersonalizedData(request);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
