package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.responses.BookRankingToNumberResponse;
import com.poly.DATN_BookWorms.responses.BookRankingToSalesResponse;
import com.poly.DATN_BookWorms.responses.CategoryRanking;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SalesAnalysisService {
    double getMonthSales(Date startDate, Date endDate);

    int getMonthOrder(Date startDate, Date endDate);

    int getProductView(Integer shopId);

    List<CategoryRanking> getCategoryRanking();

    List<BookRankingToSalesResponse> getBookRankingToSales();

    List<BookRankingToNumberResponse> getBookRankingToNumber();

    List<Book> getBookRankingToView();
}
