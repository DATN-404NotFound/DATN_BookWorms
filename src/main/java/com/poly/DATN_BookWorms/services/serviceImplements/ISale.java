package com.poly.DATN_BookWorms.services.serviceImplements;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repositories.DiscountcodesRepo;
import com.poly.DATN_BookWorms.repositories.HassalesRepo;
import com.poly.DATN_BookWorms.services.ShopOnlinesService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.SessionService;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repositories.SalesRepo;
import com.poly.DATN_BookWorms.services.SaleService;

@Service
public class ISale implements SaleService{

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
	public List<Sale> findAll() {
		// TODO Auto-generated method stub
		return saleRepo.findAll();
	}

	@Override
	public Sale findById(String id) {
		logger.info("find sale with saleid : {}",id);
		return saleRepo.findById(id).get();
	}

	@Override
	public Sale create(Sale sale) {
		Account account = session.get("user");
		LocalDate currentDate = LocalDate.now();
		ShopOnline shoponline = shopOnlinesService.findShoponlinesByUserId(account.getUserid());
		String authorityId = crc32Sha256.getCodeCRC32C(sale.getPromotionname() + sale.getStatuses());
		sale.setCouoponcode(authorityId);
		sale.setPromotionname(sale.getPromotionname());
		sale.setCreateat(new Date());
		sale.setDescriptions(sale.getDescriptions());
		sale.setDiscountpercentage(sale.getDiscountpercentage());
		sale.setStatuses(sale.getStatuses());
		sale.setIntendfor(sale.getIntendfor());
		sale.setShopid(shoponline.getShopid());
		return saleRepo.save(sale);
	}
	@Override
	public Sale update(Sale sale) {
		logger.info("update sale with sale : {}",sale);
		return saleRepo.save(sale);
	}

	@Override
	public void delete(String id) {
		logger.info("delete sales with id : {}",id);
		saleRepo.deleteById(id);;
	}

	@Override
	public List<Sale> saleOfShopIntendFor(String intendFor){
		logger.info("get list sales with intendFor : {}",intendFor);
		return saleRepo.sales_of_shop_for_intendfor(intendFor);
	}

	@Override
	public List<Sale> saleByShopAndByIntendFor(int shopId, String intendFor) {
		// TODO Auto-generated method stub
		return  saleRepo.getSaleByShopAndByIntendfor(shopId, intendFor);
	}


	public List<Sale> findAllByShopid(String intendfor, Integer shopid) {
		List<Sale> allSales = saleRepo.findAllByshopid(shopid);
		List<Sale> filteredSales = allSales.stream()
				.filter(sale -> sale.getIntendfor().equals(intendfor) && sale.isIsdelete())
				.collect(Collectors.toList());

		return filteredSales;
	}

	@Override
	public Sale save(Sale sales) {
		
		String randomCode = RandomStringUtils.randomAlphanumeric(10);
		sales.setCouoponcode(randomCode);
		sales.setShopid(null);
		sales.setMinprice(20000.00);
		return saleRepo.save(sales);
	}

	
}
