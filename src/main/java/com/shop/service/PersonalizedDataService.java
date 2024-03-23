package com.shop.service;

import com.shop.dto.ShopperRequest;
import com.shop.dto.ShopperResponse;

public interface PersonalizedDataService {
    ShopperResponse getPersonalizedData(ShopperRequest request);
}
