package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Files;
import com.poly.DATN_BookWorms.repo.FilesRepo;
import com.poly.DATN_BookWorms.service.FileShopService;


@Service
public class FilesShopServiceImp implements FileShopService {

	@Autowired
	FilesRepo filesRepo;
	@Override
	public List<Files> getFileByShop(Integer shopid) {
		// TODO Auto-generated method stub
		return filesRepo.getFilesByShopID(shopid) ;
	}

}
