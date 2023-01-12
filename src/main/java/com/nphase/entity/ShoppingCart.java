package com.nphase.entity;

import com.nphase.exception.AppException;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

public class ShoppingCart {

    private static final Integer PRODUCT_CATEGORY_DISCOUNT_FROM_EXCLUSIVELY = 3;
    private static final BigDecimal PRODUCT_CATEGORY_DISCOUNT_VALUE = BigDecimal.valueOf( 0.1 );

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
                       .collect( groupingBy( Product.groupingByCategoryFunc(),
                                             toUnmodifiableList() ) )
                       .values()
                       .stream()
                       .map( this::calculateTotalPriceForCategoryGroup )
                       .reduce( BigDecimal::add )
                       .orElse( BigDecimal.ZERO );
    }

    private BigDecimal calculateTotalPriceForCategoryGroup( final List<Product> products ) {

        if( isAppropriateForProductCategoryDiscount( products ) ) {
            return products.stream()
                    .map( Product::calculateTotalPrice )
                    .reduce( BigDecimal::add )
                    .map( e -> e.multiply( BigDecimal.ONE.subtract( PRODUCT_CATEGORY_DISCOUNT_VALUE ) ) )
                    .orElse( BigDecimal.ZERO );
        } else {
            return products.stream()
                    .map( Product::calculateTotalPrice )
                    .reduce( BigDecimal::add )
                    .orElse( BigDecimal.ZERO );
        }

    }

    private boolean isAppropriateForProductCategoryDiscount( final List<Product> products ) {

        return products.stream()
                       .map( Product::getQuantity )
                       .reduce( 0, Integer::sum ) > PRODUCT_CATEGORY_DISCOUNT_FROM_EXCLUSIVELY;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}
