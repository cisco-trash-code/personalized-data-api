package com.shop.service.impl;

import com.shop.dto.Shelf;
import com.shop.dto.ShopperRequest;
import com.shop.exception.InvalidPageSizeException;
import com.shop.exception.InvalidRequestException;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.PersonalizedDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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
    public Page<Product> getPersonalizedData(ShopperRequest request, int page, int size) {
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

        return getPaginatedProductList(productList, page, size);
    }

    private Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    private Page<Product> getPaginatedProductList(List<Product> productList, int page, int size) {
        double expectedPage = Math.ceil((double) productList.size() / size);

        if (page >= expectedPage) {
            throw new InvalidPageSizeException("Invalid page size");
        }

        if (size > 100) {
            throw new InvalidPageSizeException("Maximum page size exceeds");
        }

        final Page<Product> products;
        Pageable pageable = PageRequest.of(page,size);

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), productList.size());

        if (!productList.isEmpty()) {
            products = new PageImpl<>(productList.subList(start, end), pageable, productList.size());
        } else {
            products = new PageImpl<>(productList, pageable, 0);
        }
        return products;
    }

}
