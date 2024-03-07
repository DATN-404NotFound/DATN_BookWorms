package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.File;
import com.poly.DATN_BookWorms.repositories.FilesRepo;
import com.poly.DATN_BookWorms.services.FileShopService;


@Service
public class IFilesShop implements FileShopService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	FilesRepo filesRepo;
	@Override
	public List<File> getFileByShop(Integer shopid) {
		// TODO Auto-generated method stub
		logger.info("find list Files of Shop by shopid : {} ",shopid);
		return filesRepo.getFilesByShopID(shopid) ;
	}
	@Override
	public List<File> findAll() {
		// TODO Auto-generated method stub
		return filesRepo.findAll();
	}

}
