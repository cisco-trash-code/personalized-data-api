package com.shop.service.impl;

import com.shop.dto.Shelf;
import com.shop.dto.ShopperRequest;
import com.shop.dto.ShopperResponse;
import com.shop.exception.InvalidPageSizeException;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.PersonalizedDataService;
import com.shop.util.RequestValidation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalizedDataServiceImpl implements PersonalizedDataService {

    @Value("${max-page-size}")
    private int maxPageSize;

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(PersonalizedDataServiceImpl.class);

    @Override
    public ShopperResponse getPersonalizedData(ShopperRequest request, int page, int size) {
        logger.info("PersonalizedDataServiceImpl: getPersonalizedData(): accessed");

        RequestValidation.validateRequest(request);

        List<Product> productList = new ArrayList<>();

        request.getShelf()
                .stream()
                .sorted(Comparator.comparing(Shelf::getRelevancyScore).reversed())
                .forEach(i -> {
                    Optional<Product> product = getProductById(i.getProductId());
                    product.ifPresent(productList::add);
                });

        Page<Product> products = getPaginatedProductList(productList, page, size);

        logger.info("PersonalizedDataServiceImpl: getPersonalizedData(): success");

        return new ShopperResponse(request.getShopperId(), products);
    }

    /* get product by product id */
    private Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    /* return paginated list */
    private Page<Product> getPaginatedProductList(List<Product> productList, int page, int size) {

        double expectedPage = Math.ceil((double) productList.size() / size);

        if (page >= expectedPage && !productList.isEmpty()) {
            logger.error("PersonalizedDataServiceImpl: getPaginatedProductList(): Invalid page size");
            throw new InvalidPageSizeException("Invalid page size");
        }

        if (size > maxPageSize) {
            logger.error("PersonalizedDataServiceImpl: getPaginatedProductList(): Maximum page size exceeds");
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
