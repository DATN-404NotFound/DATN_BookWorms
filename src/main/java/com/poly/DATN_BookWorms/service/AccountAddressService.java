package com.poly.DATN_BookWorms.service;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Addressusers;

public interface AccountAddressService {
		
	public List<Addressusers> getAdressByUser(String userid);
	
	
}
