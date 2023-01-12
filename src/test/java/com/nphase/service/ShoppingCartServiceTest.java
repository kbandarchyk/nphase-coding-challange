package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    public void calculatesPrice()  {
        final var cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 2),
                new Product("Coffee", BigDecimal.valueOf(6.5), 1)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(result, BigDecimal.valueOf(16.5));
    }

    @Test
    public void calculatePriceWithDiscount() {

        final var cart = new ShoppingCart( List.of( new Product("Tea", BigDecimal.valueOf(5.0), 5),
                                                    new Product("Coffee", BigDecimal.valueOf(3.5), 3) ) );

        final var actualResult = cart.calculateTotalPrice();

        Assertions.assertEquals( actualResult.compareTo( BigDecimal.valueOf(33.0) ), 0 );

    }

}