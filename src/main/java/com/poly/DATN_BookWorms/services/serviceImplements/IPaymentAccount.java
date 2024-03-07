package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.PaymentAccount;
import com.poly.DATN_BookWorms.repositories.PaymentaccountsRepo;
import com.poly.DATN_BookWorms.services.PaymentAccountService;


@Service
public class IPaymentAccount implements PaymentAccountService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired 
	PaymentaccountsRepo paymentaccountsRepo;
	
	@Override
	public List<PaymentAccount> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentAccount> findWithUser(String userid) {
		// TODO Auto-generated method stub
		logger.info("find list PaymentAccounts by userid : {}",userid);
		return paymentaccountsRepo.findWithUser(userid);
	}

	@Override
	public PaymentAccount findById(String paycount) {
		return paymentaccountsRepo.findById(paycount).get();
	}

	@Override
	public void save(PaymentAccount paymentaccount) {
		paymentaccountsRepo.save(paymentaccount);
	}

}
