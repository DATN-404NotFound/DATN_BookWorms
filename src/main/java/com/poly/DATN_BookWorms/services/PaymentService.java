package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
	List<Payment> saveAll(List<Payment> payments);

}
