package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.entities.ShopOnline;
import com.poly.DATN_BookWorms.repositories.ShoponlinesRepo;
import com.poly.DATN_BookWorms.services.ShopOnlinesService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IShopOnlines implements ShopOnlinesService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    ShoponlinesRepo shoponlinesRepo;

    @Override
    public List<ShopOnline> getAllListShop() {
        return shoponlinesRepo.findAll();
    }

    @Override
    public ShopOnline findById(Integer id) {
        // Sử dụng repository để tìm một ShopOnline theo ID
        logger.info("findById with shopid : {}", id);
        return shoponlinesRepo.findById(id).orElse(null);
    }

    @Override
    public ShopOnline findShoponlinesByUserId(String userId) {
        return shoponlinesRepo.findByUserId(userId);
    }
}
