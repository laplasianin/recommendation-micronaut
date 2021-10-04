package com.klevu.task.repository;

import com.klevu.task.model.Product;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
