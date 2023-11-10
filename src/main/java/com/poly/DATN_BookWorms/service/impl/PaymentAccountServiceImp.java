package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Paymentaccounts;
import com.poly.DATN_BookWorms.repo.PaymentaccountsRepo;
import com.poly.DATN_BookWorms.service.PaymentAccountService;


@Service
public class PaymentAccountServiceImp implements PaymentAccountService {

	@Autowired 
	PaymentaccountsRepo paymentaccountsRepo;
	
	@Override
	public List<Paymentaccounts> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paymentaccounts> findWithUser(String userid) {
		// TODO Auto-generated method stub
		return paymentaccountsRepo.findWithUser(userid);
	}

}
