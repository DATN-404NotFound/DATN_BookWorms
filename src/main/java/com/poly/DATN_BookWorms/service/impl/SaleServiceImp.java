package com.poly.DATN_BookWorms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repo.DiscountcodesRepo;
import com.poly.DATN_BookWorms.repo.HassalesRepo;
import com.poly.DATN_BookWorms.service.ShopOnlinesService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repo.SalesRepo;
import com.poly.DATN_BookWorms.service.SaleService;

@Service
public class SaleServiceImp implements SaleService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	SalesRepo saleRepo;
	@Autowired
	HassalesRepo hassalesRepo;
	@Autowired
	DiscountcodesRepo discountcodesRepo;
	@Autowired
	SessionService session;
	@Autowired
	CRC32_SHA256 crc32Sha256;
	@Autowired
	ShopOnlinesService shopOnlinesService;
	@Override
	public List<Sales> findAll() {
		// TODO Auto-generated method stub
		return saleRepo.findAll();
	}

	@Override
	public Sales findById(String id) {
		logger.info("find sale with saleid : {}",id);
		return saleRepo.findById(id).get();
	}

	@Override
	public Sales create( String promotionname, Date createat, String descriptions, BigDecimal discountpercentage, String statuses, String intendfor) {
		Account account = session.get("user");
	Shoponlines shoponlines = shopOnlinesService.findShoponlinesByUserId(account.getUserid());
		Sales sales = new Sales();
		String authorityId = crc32Sha256.getCodeCRC32C(promotionname + statuses);
		sales.setCouoponcode(authorityId);
		sales.setPromotionname(promotionname);
		sales.setCreateat(createat);
		sales.setDescriptions(descriptions);
		sales.setDiscountpercentage(discountpercentage);
		sales.setStatuses(statuses);
		sales.setIntendfor(intendfor);
		sales.setShopid(shoponlines.getShopid());
		return saleRepo.save(sales);
	}

	@Override
	public Sales update(Sales sale) {
		logger.info("update sale with sale : {}",sale);
		return saleRepo.save(sale);
	}

	@Override
	public void delete(String id) {
		logger.info("delete sales with id : {}",id);
		saleRepo.deleteById(id);;
	}

	@Override
	public List<Sales> saleOfShopIntendFor(String intendFor) {
		return null;
	}


	@Override
	public List<Sales> saleByShopAndByIntendFor(int shopId, String intendFor) {
		logger.info("get list sales with intendFor : {}",intendFor);
		return saleRepo.sales_of_shop_for_intendfor(intendFor);
	}


//	public void addVoucherWithDiscountCodeAndSale(String PromotionName, String Descriptions, BigDecimal DiscountPercentage, String Intendfor, String Tagetbuyer, Integer[] Bookid, String saleId, String userId, Date startDiscount, Date endDiscount,
//                                                  Boolean isDelete, Double minPrice, String status,
//                                                  Date startTime, Date endTime) {
//		Account account = session.get("user");
//		Shoponlines shoponlines = shopOnlinesService.findShoponlinesByUserId(account.getUserid());
//		// Create a new discount code
//		Sales sales = new Sales();
////		sales.setCouoponcode();
//		sales.setPromotionname(PromotionName);
//		sales.setCreateat(new Date(System.currentTimeMillis()));
//		sales.setDescriptions(Descriptions);
//		sales.setDiscountpercentage(DiscountPercentage);
//		sales.setStatuses("PH");
//		sales.setIntendfor(Intendfor);
//		sales.setShopid(shoponlines.getShopid());
//
//		if (Tagetbuyer == "Books"){
//			Hassales sale = new Hassales();
//			for (Integer bookId : Bookid) {
//				sale.setBookid(bookId);
//				sale.setSaleid(saleId);
//				sale.setStarttime(startTime);
//				sale.setEndtime(endTime);
//				hassalesRepo.save(sale);
//			}
//		}
//		else {
//			Discountcodes discountCode = new Discountcodes();
//			discountCode.setSaleid(saleId);
//			discountCode.setUserid(userId);
//			discountCode.setStartdiscount(startDiscount);
//			discountCode.setEnddiscount(endDiscount);
//			discountCode.setIsdelete(isDelete);
//			discountCode.setMinprice(minPrice);
//			discountCode.setStatus(status);
//			discountcodesRepo.save(discountCode);
//		}
//		// Create a new sale
//
//
//		// Save the discount code and sale
//
//
//	}


}
