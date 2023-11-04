package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Discountcodes;
import com.poly.DATN_BookWorms.repo.DiscountcodesRepo;
import com.poly.DATN_BookWorms.service.DiscountCodeService;


@Service
public class DiscountCodeServiceImp implements DiscountCodeService {

	@Autowired
	DiscountcodesRepo discountcodesRepo;

	@Override
	public List<Discountcodes> findAll() {
		// TODO Auto-generated method stub
		
		return discountcodesRepo.findAll();
	}

	@Override
	public Discountcodes findSalesId(String salesid, String userid) {
		// TODO Auto-generated method stub
		return  discountcodesRepo.findSalesId(salesid, userid);
	}

	@Override
	public Discountcodes findById(Integer discountid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Discountcodes create(Discountcodes discount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer discount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Discountcodes update(Discountcodes discountid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Discountcodes> findDisountForSys(String userid) {
		// TODO Auto-generated method stub
		return discountcodesRepo.findDisountForSys(userid);
	}

}
