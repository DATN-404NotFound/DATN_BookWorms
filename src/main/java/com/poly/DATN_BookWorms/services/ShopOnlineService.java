package com.poly.DATN_BookWorms.services;


import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopOnlineService {
    List<ShopOnline> findAll();

    ShopOnline findById(Integer id);

    ShopOnline create(Book book);

    ShopOnline update(Book book);
}
