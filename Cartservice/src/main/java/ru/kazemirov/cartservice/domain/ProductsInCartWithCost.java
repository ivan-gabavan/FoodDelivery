package ru.kazemirov.cartservice.domain;

public class ProductsInCartWithCost extends ProductsInCart{
    private Double cost;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ProductsInCartWithCost(Double cost) {
        this.cost = cost;
    }

    public ProductsInCartWithCost(Integer productId, Integer count, Integer userId, Double cost) {
        super(productId, count, userId);
        this.cost = cost;
    }
}
