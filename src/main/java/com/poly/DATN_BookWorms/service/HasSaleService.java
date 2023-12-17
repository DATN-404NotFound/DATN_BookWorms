package com.poly.DATN_BookWorms.service;

import com.poly.DATN_BookWorms.entities.Hassales;
import com.poly.DATN_BookWorms.entities.Sales;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HasSaleService {
    List<Hassales> findAllByCouponCode(String couponCode);

    Hassales saveHassales(Hassales hassales);

    List<Hassales> findAllBysaleid(String saleId);


    @Transactional
    void deleteHassalesById(Integer hassaleId);

    List<Hassales> findAll();

    Integer findHasSaleIdByBookId(Integer bookId, String saleId);
}
