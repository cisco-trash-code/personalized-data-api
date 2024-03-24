package com.shop.service;

import com.shop.dto.ShopperRequest;
import com.shop.model.Product;
import org.springframework.data.domain.Page;

public interface PersonalizedDataService {
    Page<Product> getPersonalizedData(ShopperRequest request, int page, int size);
}
