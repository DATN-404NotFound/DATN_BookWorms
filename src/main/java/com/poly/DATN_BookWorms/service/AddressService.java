package com.poly.DATN_BookWorms.service;

import com.poly.DATN_BookWorms.entities.Addressusers;
import com.poly.DATN_BookWorms.entities.Bookings;

import java.util.List;

public interface AddressService {
    List<Addressusers> findByAll();

    public List<Addressusers> findByUserId(String userId);
}
