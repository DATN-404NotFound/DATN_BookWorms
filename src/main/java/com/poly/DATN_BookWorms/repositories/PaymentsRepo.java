package com.poly.DATN_BookWorms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.DATN_BookWorms.entities.Payment;

public interface PaymentsRepo extends JpaRepository<Payment, String>{

}
