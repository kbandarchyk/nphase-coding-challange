package com.nphase.entity;

import com.nphase.exception.AppException;

import java.math.BigDecimal;

public class Product {

    private static final Integer DISCOUNT_QUANTITY_FROM = 4;
    private static final BigDecimal DISCOUNT = BigDecimal.valueOf( 0.1 );

    private final String name;
    private final BigDecimal pricePerUnit;
    private final int quantity;

    public Product( final String name,
                    final BigDecimal pricePerUnit,
                    final int quantity ) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;

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
    }

    public BigDecimal calculateTotalPrice() {

        if ( quantity >= DISCOUNT_QUANTITY_FROM ) {
            return pricePerUnit.multiply( BigDecimal.valueOf( quantity ) )
                               .multiply( BigDecimal.ONE.subtract( DISCOUNT ) );
        } else {
            return pricePerUnit.multiply( BigDecimal.valueOf( quantity ) );
        }

    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                '}';
    }
}
