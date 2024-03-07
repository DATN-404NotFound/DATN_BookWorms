package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.ShippingUnit;
import com.poly.DATN_BookWorms.repositories.ShippingunitsRepo;
import com.poly.DATN_BookWorms.services.ShippingUnitService;


@Service
public class IShippingUnit implements ShippingUnitService {
	
	@Autowired
	ShippingunitsRepo shippingunitsRepo;
	
	@Override
	public List<ShippingUnit> getAll() {
		// TODO Auto-generated method stub
		return shippingunitsRepo.findAll();
	}

}
