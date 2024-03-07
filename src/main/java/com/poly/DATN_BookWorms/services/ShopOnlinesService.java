package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.ShopOnline;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface ShopOnlinesService {


    List<ShopOnline> getAllListShop();

    ShopOnline findById(Integer cid);

    ShopOnline findShoponlinesByUserId(String userId);
}
