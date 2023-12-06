package com.poly.DATN_BookWorms.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisFinance {
    private Float monthPaid;
    private Float totalPaid;
    private Float totalUnpaid;
    
}
