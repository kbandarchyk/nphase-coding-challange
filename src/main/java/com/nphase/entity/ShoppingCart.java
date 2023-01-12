package com.nphase.entity;

import com.nphase.exception.AppException;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

public class ShoppingCart {

    private final List<Product> products;
    private final DiscountConfigs discountConfigs;

    public ShoppingCart( final List<Product> products,
                         final DiscountConfigs discountConfigs ) {
        this.products = products;
        this.discountConfigs = discountConfigs;

        validate();
    }

    private void validate() {
        if ( products == null ) {
            throw new AppException( "ShoppingCart products can not be null in {0}", this );
        }
        if ( discountConfigs == null ) {
            throw new AppException( "ShoppingCart discountConfigs can not be null in {0}", this );
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
                    .map( e -> e.calculateTotalPrice( discountConfigs.getProductQuantityConfig() ) )
                    .reduce( BigDecimal::add )
                    .map( e -> e.multiply( BigDecimal.ONE.subtract( discountConfigs.getProductCategoryConfig()
                                                                                   .getDiscountValue() ) ) )
                    .orElse( BigDecimal.ZERO );
        } else {
            return products.stream()
                    .map( e -> e.calculateTotalPrice( discountConfigs.getProductQuantityConfig() ) )
                    .reduce( BigDecimal::add )
                    .orElse( BigDecimal.ZERO );
        }

    }

    private boolean isAppropriateForProductCategoryDiscount( final List<Product> products ) {

        return products.stream()
                       .map( Product::getQuantity )
                       .reduce( 0, Integer::sum ) > discountConfigs.getProductCategoryConfig()
                                                                   .getDiscountFromExclusively();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                ", discountConfigs=" + discountConfigs +
                '}';
    }
}
