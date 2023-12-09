package com.poly.DATN_BookWorms.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Discountcodebyuserid {

	private Date startdiscount;
    private String promotionname;
    private String fileName;
}
