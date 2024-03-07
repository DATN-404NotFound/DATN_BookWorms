package com.poly.DATN_BookWorms.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisFinanceResponse {
    private Float monthPaid;
    private Float totalPaid;
    private Float totalUnpaid;
}
