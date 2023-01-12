package com.nphase.entity;

import com.nphase.exception.AppException;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {

    private final List<Product> products;

    public ShoppingCart( final List<Product> products ) {
        this.products = products;

        validate();
    }

    private void validate() {
        if ( products == null ) {
            throw new AppException( "ShoppingCart can not be null in {0}", this );
        }
    }

    public BigDecimal calculateTotalPrice() {
        return products.stream()
                .map( Product::calculateTotalPrice )
                .reduce( BigDecimal::add )
                .orElse( BigDecimal.ZERO );
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}
