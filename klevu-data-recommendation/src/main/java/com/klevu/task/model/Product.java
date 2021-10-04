package com.klevu.task.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }
}