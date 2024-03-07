package com.poly.DATN_BookWorms.responses;

import lombok.Data;

@Data
public class DetailBookingResponse {
    String bookname;
    Integer quantity;

    public DetailBookingResponse(String bookname, int quantity) {
        this.bookname = bookname;
        this.quantity = quantity;
    }
}

