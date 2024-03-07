package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Payment;
import com.poly.DATN_BookWorms.repositories.PaymentsRepo;
import com.poly.DATN_BookWorms.services.PaymentService;


@Service
public class IPayment implements PaymentService{
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	PaymentsRepo paymentsRepo;
	
	@Override
	public List<Payment> saveAll(List<Payment> payments) {
		// TODO Auto-generated method stub
		logger.info("save all payments by payments : {}",payments.size());
		return paymentsRepo.saveAll(payments);
	}

}
