package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.HasSale;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HasSaleService {
    List<HasSale> findAllByCouponCode(String couponCode);

    HasSale saveHassales(HasSale hasSale);

    List<HasSale> findAllBysaleid(String saleId);

    @Transactional
    void deleteHassalesById(Integer hassaleId);

    List<HasSale> findAll();

    Integer findHasSaleIdByBookId(Integer bookId, String saleId);
}
