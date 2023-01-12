package com.nphase.entity;

import com.nphase.exception.AppException;

import java.util.Objects;

public class ProductCategory {

    private final String name;

    public ProductCategory( final String name ) {
        this.name = name;

        validate();
    }

    private void validate() {
        if ( name == null || name.trim().length() == 0 ) {
            throw new AppException( "ProductCategory name can not be null or empty in {0}", this );
        }
    }

    @Override
    public boolean equals( final Object o ) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final var that = (ProductCategory) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
