package com.poly.DATN_BookWorms.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DetailBookingResponse {
	String bookname;
	Integer quantity;
	
	public DetailBookingResponse(String bookname, int quantity) {
		this.bookname = bookname;
		this.quantity = quantity;
	}
}
