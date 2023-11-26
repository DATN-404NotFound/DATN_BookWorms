package com.poly.DATN_BookWorms.service;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PaymentShopService {
    void save(PaymentShop paymentShop);

    List<PaymentShop> findByShopId(Integer shopId);

    Float getMonthPaid(Integer shopId,Date startDate, Date endDate, boolean statusPayment);

    Float getTotal(Integer shopId,boolean statusPayment);
}
