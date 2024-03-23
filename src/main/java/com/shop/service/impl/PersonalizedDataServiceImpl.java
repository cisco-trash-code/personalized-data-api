package com.shop.service.impl;

import com.shop.dto.Shelf;
import com.shop.dto.ShopperRequest;
import com.shop.dto.ShopperResponse;
import com.shop.exception.InvalidRequestException;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.PersonalizedDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalizedDataServiceImpl implements PersonalizedDataService {

    private final ProductRepository productRepository;

    @Override
    public ShopperResponse getPersonalizedData(ShopperRequest request) {
        if (request.getShelf().isEmpty()) {
            throw new InvalidRequestException("Shelf is empty");
        }

        List<Product> productList = new ArrayList<>();

        request.getShelf()
                .stream()
                .sorted(Comparator.comparing(Shelf::getRelevancyScore).reversed())
                .forEach(i -> {
                    Optional<Product> product = getProductById(i.getProductId());
                    product.ifPresent(productList::add);
                });

        return new ShopperResponse(request.getShopperId(), productList);
    }

    private Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

}
