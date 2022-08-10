package ru.kazemirov.cartservice.domain;


import javax.persistence.*;

@Entity
@IdClass(ProductOfUser.class)
public class ProductsInCart {
    @Id
    private Integer userId;
    @Id
    private Integer productId;
    private Integer count;


    public ProductsInCart() {
    }
    @Override
    public String toString() {
        return "userId=" + userId +
                ", productId=" + productId +
                ", count=" + count;
    }

    public ProductsInCart(Integer productId, Integer count, Integer userId) {
        this.productId = productId;
        this.count = count;
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
