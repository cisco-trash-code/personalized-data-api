package com.shop.controller;

import com.shop.dto.ShopperRequest;
import com.shop.dto.ShopperResponse;
import com.shop.service.PersonalizedDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalized-data")
public class PersonalizedDataController {

    private final PersonalizedDataService personalizedDataService;

    @PostMapping("/")
    public ResponseEntity<ShopperResponse> getPersonalizedData(@RequestBody ShopperRequest request,
                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        ShopperResponse data = personalizedDataService.getPersonalizedData(request, page, size);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
