package com.poly.DATN_BookWorms.beans;

import com.poly.DATN_BookWorms.entities.Books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRankingToSales {
    private Books book;
    private double sale;

    
    
}
