package com.nphase.entity;

import com.nphase.exception.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    public void createProductWithNullName() {
        assertThrows(AppException.class, () -> new Product( null, BigDecimal.valueOf(10), 2 ) );
    }

    @Test
    public void createProductWithEmptyName() {
        assertThrows(AppException.class, () -> new Product( "  ", BigDecimal.valueOf(10), 2 ) );
    }

    @Test
    public void createProductWithNullPrice() {
        assertThrows(AppException.class, () -> new Product( "Tea", null, 2 ) );
    }

    @Test
    public void createProductWithNegativePrice() {
        assertThrows(AppException.class, () -> new Product( "Tea", BigDecimal.valueOf( -10 ), 2 ) );
    }

    @Test
    public void createProductWithZeroQuantity() {
        assertThrows(AppException.class, () -> new Product( "Tea", BigDecimal.valueOf( 10 ), 0 ) );
    }

    @Test
    public void createProductWithNegativeQuantity() {
        assertThrows(AppException.class, () -> new Product( "Tea", BigDecimal.valueOf( 10 ), -1 ) );
    }

    @Test
    public void calculateTotalPrice() {

        final var product = new Product( "Tea", BigDecimal.valueOf( 10 ), 2 );

        final var actualResult = product.calculateTotalPrice();

        Assertions.assertEquals( actualResult, BigDecimal.valueOf(20) );

    }
}