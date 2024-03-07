package com.poly.DATN_BookWorms.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    Long bookid;
    String bookname;
    String image;
    Double price;
    String shopname;
}
