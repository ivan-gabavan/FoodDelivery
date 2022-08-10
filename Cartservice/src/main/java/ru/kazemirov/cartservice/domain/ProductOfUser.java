package ru.kazemirov.cartservice.domain;

import java.io.Serializable;
import java.util.Objects;

public class ProductOfUser implements Serializable {
    private Integer userId;
    private Integer productId;

    public ProductOfUser() {
    }

    public ProductOfUser(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOfUser that = (ProductOfUser) o;
        return userId.equals(that.userId) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
