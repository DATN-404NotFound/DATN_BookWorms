package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Hassales;
import com.poly.DATN_BookWorms.repo.HassalesRepo;
import com.poly.DATN_BookWorms.service.HasSaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HasSaleServiceImp implements HasSaleService {
    HassalesRepo hassalesRepo;
    @Override
    public List<Hassales> findAllByCouponCode(String couponCode) {

        return hassalesRepo.findAllByCouponCode(couponCode);
    }
}
