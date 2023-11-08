package com.poly.DATN_BookWorms.service;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Categories;

public interface TypeBookService {

		public List<Categories> getCategoriesWithShop(Integer shopid);
}
