package com.poly.DATN_BookWorms.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesResponse {
    private double monthlySales;
    private int orders;
    private double conversionRate;
    private double pagesViews;
    private double  salesPerOrder;
}
