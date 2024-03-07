package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.PaymentAccount;
import org.springframework.stereotype.Service;

@Service
public interface PaymentAccountService {
    List<PaymentAccount> findAll();

    List<PaymentAccount> findWithUser(String userid);

    PaymentAccount findById(String paycount);

    void save(PaymentAccount paymentaccount);
}
