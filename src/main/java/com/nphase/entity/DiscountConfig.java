package com.nphase.entity;

import com.nphase.exception.AppException;

import java.math.BigDecimal;

public class DiscountConfig {

    private final int discountFromExclusively;
    private final BigDecimal discountValue;

    public DiscountConfig( final int discountFromExclusively,
                           final BigDecimal discountValue ) {
        this.discountFromExclusively = discountFromExclusively;
        this.discountValue = discountValue;

        validate();
    }

    public int getDiscountFromExclusively() {
        return discountFromExclusively;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    private void validate() {

        if ( discountFromExclusively < 0 ) {
            throw new AppException( "DiscountConfig discountFromExclusively can not be less than 0 in {0}", this );
        }

        if (discountValue == null ) {
            throw new AppException( "DiscountConfig discountValue can not be null in {0}", this );
        }

        if (discountValue.compareTo( BigDecimal.ZERO ) < 0 ) {
            throw new AppException( "DiscountConfig discountValue can not be less than 0 in {0}", this );
        }
    }

    @Override
    public String toString() {
        return "DiscountConfig{" +
                "discountFromExclusively=" + discountFromExclusively +
                ", discountValue=" + discountValue +
                '}';
    }

}
