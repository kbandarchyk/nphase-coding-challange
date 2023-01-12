package com.nphase.service;

import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(final ShoppingCart shoppingCart ) {
        return shoppingCart.calculateTotalPrice();
    }
}
