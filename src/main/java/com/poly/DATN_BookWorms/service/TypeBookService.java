package com.poly.DATN_BookWorms.service;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Categories;
import com.poly.DATN_BookWorms.entities.Typebooks;

public interface TypeBookService {

		public List<Categories> getCategoriesWithShop(Integer shopid);



    Typebooks create(Books books, Categories categories);
}
