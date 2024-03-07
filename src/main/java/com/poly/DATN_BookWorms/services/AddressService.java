package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.AddressUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<AddressUser> findByAll();

    List<AddressUser> findByUserId(String userId);

    AddressUser create(AddressUser addressusers);

    AddressUser update(AddressUser addressusers);

    void delete(String addressusers);

    AddressUser byAddressUserId(String addressusersId);

}
