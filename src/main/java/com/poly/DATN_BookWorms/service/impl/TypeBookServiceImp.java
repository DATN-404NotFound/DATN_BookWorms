package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Categories;
import com.poly.DATN_BookWorms.repo.TypebooksRepo;
import com.poly.DATN_BookWorms.service.TypeBookService;


@Service
public class TypeBookServiceImp implements TypeBookService {
	@Autowired
	TypebooksRepo typebooksRepo;
	
	@Override
	public List<Categories> getCategoriesWithShop(Integer shopid) {
		// TODO Auto-generated method stub
		return typebooksRepo.listCategoriesByType(shopid);
	}

}
