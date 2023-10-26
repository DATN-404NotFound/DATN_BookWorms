package com.poly.DATN_BookWorms.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
public class BookResponse {
    Integer bookid;
    String bookname;
    String image;
    Double price;
    String shopname;
    public BookResponse(Integer bookid, String bookname, String image, Double price, String shopname) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.image = image;
        this.price = price;
        this.shopname = shopname;
    }
}
