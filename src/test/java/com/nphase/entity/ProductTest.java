package com.nphase.entity;

import com.nphase.exception.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    public void createProductWithNullName() {
        assertThrows(AppException.class, () -> new Product( null, BigDecimal.valueOf(10), 2, new ProductCategory( "drinks" ) ) );
    }

    @Test
    public void createProductWithEmptyName() {
        assertThrows(AppException.class, () -> new Product( "  ", BigDecimal.valueOf(10), 2, new ProductCategory( "drinks" ) ) );
    }

    @Test
    public void createProductWithNullPrice() {
        assertThrows(AppException.class, () -> new Product( "Tea", null, 2, new ProductCategory( "drinks" ) ) );
    }

    @Test
    public void createProductWithNegativePrice() {
        assertThrows(AppException.class, () -> new Product( "Tea", BigDecimal.valueOf( -10 ), 2, new ProductCategory( "drinks" ) ) );
    }

    @Test
    public void createProductWithZeroQuantity() {
        assertThrows(AppException.class, () -> new Product( "Tea", BigDecimal.valueOf( 10 ), 0, new ProductCategory( "drinks" ) ) );
    }

    @Test
    public void createProductWithNegativeQuantity() {
        assertThrows(AppException.class, () -> new Product( "Tea", BigDecimal.valueOf( 10 ), -1, new ProductCategory( "drinks" ) ) );
    }

    @Test
    public void createProductWithNullCategory() {
        assertThrows(AppException.class, () -> new Product( "Tea", BigDecimal.valueOf( 10 ), 2, null ) );
    }

    @Test
    public void calculateTotalPriceWithoutDiscount() {

        final var product = new Product( "Tea", BigDecimal.valueOf( 10 ), 2, new ProductCategory( "drinks" ) );

        final var actualResult = product.calculateTotalPrice( new DiscountConfig( 3, BigDecimal.valueOf( 0.1 ) ) );

        Assertions.assertEquals( actualResult.compareTo( BigDecimal.valueOf(20) ), 0 );

    }

    @Test
    public void calculateTotalPriceWithDiscount() {

        final var product = new Product( "Tea", BigDecimal.valueOf( 10 ), 5, new ProductCategory( "drinks" ) );

        final var actualResult = product.calculateTotalPrice( new DiscountConfig( 3, BigDecimal.valueOf( 0.1 ) ) );

        Assertions.assertEquals( actualResult.compareTo( BigDecimal.valueOf(45) ), 0 );

    }
}