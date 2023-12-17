package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Hassales;
import com.poly.DATN_BookWorms.repo.HassalesRepo;
import com.poly.DATN_BookWorms.service.HasSaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HasSaleServiceImp implements HasSaleService {
    @Autowired
    HassalesRepo hassalesRepo;
    @Override
    public List<Hassales> findAllByCouponCode(String couponCode) {
        return hassalesRepo.findAllByCouponCode(couponCode);
    }

    @Override
    public Hassales saveHassales(Hassales hassales) {
        hassalesRepo.save(hassales);
        return hassales;
    }

    @Override
    public List<Hassales> findAllBysaleid(String saleId) {
        List<Hassales> allSales = hassalesRepo.findAll();
        List<Hassales> filteredSales = allSales.stream()
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
    public List<Hassales> findAll() {
        return hassalesRepo.findAll();
    }

    @Override
    public Integer findHasSaleIdByBookId(Integer bookId, String saleId) {
        return hassalesRepo.findHasSaleIdByBookId(bookId, saleId);
    }



}
