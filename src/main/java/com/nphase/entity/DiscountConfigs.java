package com.nphase.entity;

import com.nphase.exception.AppException;

public class DiscountConfigs {

    private final DiscountConfig productQuantityConfig;
    private final DiscountConfig productCategoryConfig;

    public DiscountConfigs( final DiscountConfig productQuantityConfig,
                            final DiscountConfig productCategoryConfig ) {
        this.productQuantityConfig = productQuantityConfig;
        this.productCategoryConfig = productCategoryConfig;

        validate();
    }

    public DiscountConfig getProductQuantityConfig() {
        return productQuantityConfig;
    }

    public DiscountConfig getProductCategoryConfig() {
        return productCategoryConfig;
    }

    private void validate() {

        if( productCategoryConfig == null ) {
            throw new AppException( "DiscountConfigs productCategoryConfig can not be null in {0}", this );
        }
        if (productQuantityConfig == null ) {
            throw new AppException( "DiscountConfigs productQuantityConfig can not be null in {0}", this );
        }
    }

    @Override
    public String toString() {
        return "DiscountConfigs{" +
                "productQuantityConfig=" + productQuantityConfig +
                ", productCategoryConfig=" + productCategoryConfig +
                '}';
    }
}
