package com.poly.DATN_BookWorms.service;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import org.springframework.stereotype.Service;

@Service
public interface PaymentShopService {
    void save(PaymentShop paymentShop);
}
