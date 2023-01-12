package com.nphase.entity;

import com.nphase.exception.AppException;

import java.math.BigDecimal;
import java.util.function.Function;

public class Product {

    private static final Integer QUANTITY_DISCOUNT_FROM_EXCLUSIVELY = 3;
    private static final BigDecimal QUANTITY_DISCOUNT_VALUE = BigDecimal.valueOf( 0.1 );

    private final String name;
    private final BigDecimal pricePerUnit;
    private final int quantity;
    private final ProductCategory category;

    public Product( final String name,
                    final BigDecimal pricePerUnit,
                    final int quantity,
                    final ProductCategory category ) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.category = category;

        validate();
    }

    private void validate() {
        if ( name == null || name.trim().length() == 0 ) {
            throw new AppException( "Product name can not be null or empty in {0}", this );
        }

        if( pricePerUnit == null ) {
            throw new AppException( "Product pricePerUnit can not be null in {0}", this );
        }

        if ( pricePerUnit.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new AppException( "Product pricePerUnit can not be less than 0 in {0}", this );
        }

        if ( quantity <= 0 ) {
            throw new AppException( "Product quantity can not be less than or equal 0 in {0}", this );
        }

        if( category == null ) {
            throw new AppException( "Product category can not be null in {0}", this );
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal calculateTotalPrice() {

        if ( isAppropriateForQuantityDiscount() ) {
            return pricePerUnit.multiply( BigDecimal.valueOf( quantity ) )
                               .multiply( BigDecimal.ONE.subtract( QUANTITY_DISCOUNT_VALUE ) );
        } else {
            return pricePerUnit.multiply( BigDecimal.valueOf( quantity ) );
        }

    }

    public static Function<Product, ProductCategory> groupingByCategoryFunc() {
        return e -> e.category;
    }

    private boolean isAppropriateForQuantityDiscount() {
        return quantity > QUANTITY_DISCOUNT_FROM_EXCLUSIVELY;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}
