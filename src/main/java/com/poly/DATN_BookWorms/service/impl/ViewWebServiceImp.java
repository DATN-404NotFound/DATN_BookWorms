package com.poly.DATN_BookWorms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.ViewWeb;
import com.poly.DATN_BookWorms.repo.ViewWebRepo;
import com.poly.DATN_BookWorms.service.ViewWebService;

@Service
public class ViewWebServiceImp implements ViewWebService{
	
	@Autowired
	ViewWebRepo viewWebRepo;
	
	@Override
	public ViewWeb save(ViewWeb viewWeb) {
		// TODO Auto-generated method stub
		return viewWebRepo.save(viewWeb);
	}

}
