package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repositories.CategoriesRepo;
import com.poly.DATN_BookWorms.services.CategoryService;

@Service
public class ICategory implements CategoryService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    CategoriesRepo cateRepo;

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        return cateRepo.findAll();
    }

}
