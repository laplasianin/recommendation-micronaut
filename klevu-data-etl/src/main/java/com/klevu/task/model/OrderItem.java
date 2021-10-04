package com.klevu.task.model;

public class OrderItem {

    private final String ip;
    private final Product product;

    public OrderItem(String ip, Product product) {
        this.ip = ip;
        this.product = product;
    }

    public String getIp() {
        return ip;
    }

    public Product getProduct() {
        return product;
    }
}