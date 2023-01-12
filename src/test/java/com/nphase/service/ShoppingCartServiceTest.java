package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ProductCategory;
import com.nphase.entity.ShoppingCart;
import jdk.jfr.Category;
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
                new Product("Tea", BigDecimal.valueOf(5.0), 2, new ProductCategory( "drinks" ) ),
                new Product("Coffee", BigDecimal.valueOf(6.5), 1, new ProductCategory( "drinks" ) )
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(result, BigDecimal.valueOf(16.5));
    }

    @Test
    public void calculatePriceWithProductDiscount() {

        final var cart = new ShoppingCart( List.of( new Product("Tea", BigDecimal.valueOf(5.0), 4, new ProductCategory( "drinks" )),
                                                    new Product("Cheese", BigDecimal.valueOf(3.5), 3, new ProductCategory( "food" )) ) );

        final var actualResult = cart.calculateTotalPrice();

        Assertions.assertEquals( actualResult.compareTo( BigDecimal.valueOf(26.7) ), 0 );
    }

    @Test
    public void calculateTotalPriceWithCategoryDiscount() {

        final var shoppingCart = new ShoppingCart( List.of( new Product("Tea", BigDecimal.valueOf(5.3), 2, new ProductCategory( "drinks" ) ),
                                                            new Product("Coffee", BigDecimal.valueOf(3.5), 2, new ProductCategory( "drinks" ) ),
                                                            new Product("Cheese", BigDecimal.valueOf(8), 2, new ProductCategory( "food" ) ) ) );

        final var actualResult = shoppingCart.calculateTotalPrice();

        Assertions.assertEquals( actualResult.compareTo( BigDecimal.valueOf(31.84) ), 0);
    }

    @Test
    public void calculatePriceWithProductAndCategoryDiscount() {

        final var shoppingCart = new ShoppingCart( List.of( new Product("Tea", BigDecimal.valueOf(5.3), 5, new ProductCategory( "drinks" ) ),
                                                            new Product("Coffee", BigDecimal.valueOf(3.5), 2, new ProductCategory( "drinks" ) ),
                                                            new Product("Cheese", BigDecimal.valueOf(8), 2, new ProductCategory( "food" ) ) ) );

        final var actualResult = shoppingCart.calculateTotalPrice();

        Assertions.assertEquals( actualResult.compareTo( BigDecimal.valueOf(43.765) ), 0);
    }

}