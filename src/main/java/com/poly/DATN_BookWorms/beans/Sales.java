package com.poly.DATN_BookWorms.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales {

    private double monthlySales;

    private double orders;

    private double conversionRate;

    private double pagesViews;

    private double  salesPerOrder;

}
