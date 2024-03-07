package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.entities.HasSale;
import com.poly.DATN_BookWorms.repositories.HassalesRepo;
import com.poly.DATN_BookWorms.services.HasSaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IHasSale implements HasSaleService {
    @Autowired
    HassalesRepo hassalesRepo;
    @Override
    public List<HasSale> findAllByCouponCode(String couponCode) {
        return hassalesRepo.findAllByCouponCode(couponCode);
    }

    @Override
    public HasSale saveHassales(HasSale hasSale) {
        hassalesRepo.save(hasSale);
        return hasSale;
    }

    @Override
    public List<HasSale> findAllBysaleid(String saleId) {
        List<HasSale> allSales = hassalesRepo.findAll();
        List<HasSale> filteredSales = allSales.stream()
                .filter(sale -> sale.getSaleid().equals(saleId))
                .collect(Collectors.toList());

        return filteredSales;
    }
    @Override
    @Transactional
    public void deleteHassalesById(Integer hassaleId) {
        hassalesRepo.deleteById(hassaleId);
    }

    @Override
    public List<HasSale> findAll() {
        return hassalesRepo.findAll();
    }

    @Override
    public Integer findHasSaleIdByBookId(Integer bookId, String saleId) {
        return hassalesRepo.findHasSaleIdByBookId(bookId, saleId);
    }



}
