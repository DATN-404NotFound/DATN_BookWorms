package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.DiscountCode;
import com.poly.DATN_BookWorms.repositories.DiscountcodesRepo;
import com.poly.DATN_BookWorms.services.DiscountCodeService;


@Service
public class IDiscountCode implements DiscountCodeService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	DiscountcodesRepo discountcodesRepo;

	@Override
	public List<DiscountCode> findAll() {
		// TODO Auto-generated method stub
		
		return discountcodesRepo.findAll();
	}

	@Override
	public DiscountCode findSalesId(String salesid, String userid) {
		// TODO Auto-generated method stub
		logger.info("find Discountcodes by saleId : {} and userId : {}",salesid, userid);
		return  discountcodesRepo.findSalesId(salesid, userid);
	}

	@Override
	public DiscountCode findById(Integer discountid) {
		// TODO Auto-generated method stub
		return discountcodesRepo.findById(discountid).get();
	}

	@Override
	public DiscountCode create(DiscountCode discount) {
		// TODO Auto-generated method stub
		return discountcodesRepo.save(discount);
	}

	@Override
	public void delete(Integer discount) {
		// TODO Auto-generated method stub
		discountcodesRepo.deleteById(discount);
	}

	@Override
	public DiscountCode update(DiscountCode discountid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiscountCode> findDisountForSys(String userid) {
		// TODO Auto-generated method stub
		logger.info("find Discountcodes for hệ thống  by userId : {}", userid);
		return discountcodesRepo.findDisountForSys(userid);
	}

	@Override
	public List<DiscountCode> findDisountOfShopWithUser(String intendfor, String userid, int shopid) {
		// TODO Auto-generated method stub
		return discountcodesRepo.findDisountOfShopWithUser(intendfor, userid, shopid);
	}

	@Override
	public List<DiscountCode> findDisountByUserId(String userid) {
		// TODO Auto-generated method stub
		return discountcodesRepo.findDisountByUserId(userid);
	}



}
