package com.poly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entities.Sales;

public interface SalesRepo extends JpaRepository<Sales, String>{

}
