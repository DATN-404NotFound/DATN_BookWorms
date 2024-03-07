package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.File;
import org.springframework.stereotype.Service;

@Service
public interface FileShopService {
    List<File> getFileByShop(Integer shopid);

    List<File> findAll();
}
