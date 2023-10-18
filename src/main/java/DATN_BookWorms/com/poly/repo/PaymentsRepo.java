package com.poly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entities.Payments;

public interface PaymentsRepo extends JpaRepository<Payments, String>{

}
