package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import com.poly.DATN_BookWorms.repositories.ShoponlinesRepo;
import com.poly.DATN_BookWorms.services.AccountService;
import com.poly.DATN_BookWorms.services.ShopService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IShop implements ShopService {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	ShoponlinesRepo shoponlinesRepo;

	@Autowired
	AccountService accountService;

	@Override
	public ShopOnline findUserId(String userId) {
		logger.info("find shoponelines by userid :{}", userId);
		return shoponlinesRepo.findByUserId(userId);
	}

	@Override
	public void save(ShopOnline shoponline) {
		logger.info("save shoponelines by shoponlines :{}", shoponline.toString());
		shoponlinesRepo.save(shoponline);
	}

	@Override
	public void createShopDefaultWithUser(Account user) {
		logger.info("create shop dèalut with user :{}", user.toString());
		ShopOnline shopDefault = new ShopOnline();
		logger.info("shopdeafault :{}", shopDefault.toString());
		try {
			shopDefault.setUserid(user.getUserid());
			shopDefault.setShopname("Shop Of " + user.getUsername());
			shopDefault.setDescription("IBook is always a reliable choice for book lovers");
			shopDefault.setIsactive(true);
			shoponlinesRepo.save(shopDefault);

			//add shop to accont
			List<ShopOnline> listShopOnline  = new ArrayList<>();
			listShopOnline.add(shopDefault);
			user.setListOfShopOnline(listShopOnline);
			accountService.save(user);
			logger.info("create shopdeafault  is sucesssfully with shopDefault:{}", shopDefault.toString());
		} catch (Exception e) {
			logger.error("create shopdeafault  is failed with shopDefault : {} and exception : {}",
					shopDefault.toString(), e);
		}

	}

	@Override
	public ShopOnline findById(Integer shopId) {
		logger.info("find shoplines  with shopid : {}", shopId);
		return shoponlinesRepo.findById(shopId).get();
	}

}
