package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.repo.PaymentShopRepo;
import com.poly.DATN_BookWorms.service.PaymentShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentShopServiceImpl implements PaymentShopService {

    @Autowired
    PaymentShopRepo paymentShopRepo;
    @Override
    public void save(PaymentShop paymentShop) {
        paymentShopRepo.save(paymentShop);
    }
}
