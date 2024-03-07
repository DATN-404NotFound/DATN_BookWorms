package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.DATN_BookWorms.repositories.ShoponlinesRepo;
import com.poly.DATN_BookWorms.services.ShopOnlineService;

@Service
public class IShopOnline implements ShopOnlineService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    ShoponlinesRepo shoponlinesRepo;

    @Override
    public List<ShopOnline> findAll() {
        // TODO Auto-generated method stub
        return shoponlinesRepo.findAll();
    }

    @Override
    public ShopOnline findById(Integer id) {
        // TODO Auto-generated method stub
        return shoponlinesRepo.findById(id).get();
    }

    @Override
    public ShopOnline create(Book book) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShopOnline update(Book book) {
        // TODO Auto-generated method stub
        return null;
    }

}
