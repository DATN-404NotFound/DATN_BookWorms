package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.AddressUser;
import org.springframework.stereotype.Service;

@Service
public interface AccountAddressService {

    List<AddressUser> getAdressByUser(String userid);

    AddressUser findById(String id);

    AddressUser save(AddressUser id);

}
