package com.billing.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.finalproject.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
