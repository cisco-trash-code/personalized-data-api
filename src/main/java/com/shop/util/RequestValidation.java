package com.shop.util;

import com.shop.dto.ShopperRequest;
import com.shop.exception.InvalidRequestException;
import com.shop.service.impl.PersonalizedDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestValidation {

    private static final Logger logger = LoggerFactory.getLogger(PersonalizedDataServiceImpl.class);

    public static void validateRequest(ShopperRequest request) {

        if (request.getShopperId() == null || request.getShopperId().isEmpty() || !request.getShopperId().matches("^S-\\d+$")) {
            logger.error("PersonalizedDataServiceImpl: getPersonalizedData(): RequestValidation: Invalid shopperId");
            throw new InvalidRequestException("Invalid shopperId");
        }

        if (request.getShelf() == null || request.getShelf().isEmpty()) {
            logger.error("PersonalizedDataServiceImpl: getPersonalizedData(): RequestValidation: Shelf is empty");
            throw new InvalidRequestException("Shelf is empty");
        }
    }
}
