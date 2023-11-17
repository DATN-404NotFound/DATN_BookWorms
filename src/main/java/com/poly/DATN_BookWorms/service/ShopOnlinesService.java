package com.poly.DATN_BookWorms.service;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import org.springframework.stereotype.Service;

@Service
public interface ShopOnlinesService {

    Shoponlines findById(Integer cid);
    Shoponlines findShoponlinesByUserId(String userId);
}
