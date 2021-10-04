package com.klevu.task.model.web;

import com.klevu.task.model.Product;

import java.util.List;

public class Products {

    private List<Product> products;

    public Products() { }

    public Products(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

}