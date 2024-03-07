package com.poly.DATN_BookWorms.services.serviceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.ViewWeb;
import com.poly.DATN_BookWorms.repositories.ViewWebRepo;
import com.poly.DATN_BookWorms.services.ViewWebService;

@Service
public class IViewWeb implements ViewWebService{
	
	@Autowired
	ViewWebRepo viewWebRepo;
	
	@Override
	public ViewWeb save(ViewWeb viewWeb) {
		// TODO Auto-generated method stub
		return viewWebRepo.save(viewWeb);
	}

}
