package com.poly.DATN_BookWorms.responses;

import com.poly.DATN_BookWorms.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRankingToSalesResponse {
    private Book book;
    private double sale;

}
