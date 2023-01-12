package com.nphase.entity;

import com.nphase.exception.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTest {

    @Test
    public void createShoppingCartWithNullProducts() {
        assertThrows(AppException.class, () -> new ShoppingCart( null ) );
    }

    @Test
    public void calculateTotalPrice() {

        final var shoppingCart = new ShoppingCart( List.of( new Product("Tea", BigDecimal.valueOf(5.0), 2, new ProductCategory( "drinks" ) ),
                                                            new Product("Coffee", BigDecimal.valueOf(6.5), 1, new ProductCategory( "drinks" ) ) ) );

        final var actualResult = shoppingCart.calculateTotalPrice();

        Assertions.assertEquals( actualResult.compareTo( BigDecimal.valueOf(16.5) ), 0);
    }

}