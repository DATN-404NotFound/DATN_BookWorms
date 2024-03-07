package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Sale;
import org.springframework.stereotype.Service;


@Service
public interface SaleService {

	List<Sale> findAll();

	Sale findById(String id);



	Sale create(Sale sale);

	
	Sale update(Sale sale);

	void delete(String id);

	List<Sale> saleOfShopIntendFor(String intendFor);
	
	List<Sale> saleByShopAndByIntendFor(int shopId,String intendFor);
	




	List<Sale> findAllByShopid(String intendfor, Integer shopid);

	Sale save(Sale sale);
}
