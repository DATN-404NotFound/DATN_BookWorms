package com.poly.DATN_BookWorms.response;

<<<<<<< HEAD
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
=======
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
>>>>>>> zendyy/back_end
