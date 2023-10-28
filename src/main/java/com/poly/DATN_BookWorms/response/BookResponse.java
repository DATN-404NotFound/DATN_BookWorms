package com.poly.DATN_BookWorms.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
private String bookname;
    private String name;
    private Double price;
    private String shopname;


}
