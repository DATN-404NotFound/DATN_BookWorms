package com.poly.DATN_BookWorms.rest.controller;

import com.poly.DATN_BookWorms.beans.Sales;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.service.SalesAnalysisService;
import com.poly.DATN_BookWorms.utils.ConvertStringToDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/salesAnalysis")
public class SalesAnalysisRestController {

    @Autowired
    SalesAnalysisService salesAnalysisService;
    @Autowired
    ConvertStringToDate convertStringToDate;

    @GetMapping("/getSalesAnalysis")
    public ResponseEntity<Sales> getAccountShop(@RequestParam("month") String month) {
        Sales sales = new Sales();
//        // lấy doanh số bán hàng theo tháng
//        Date startDate = convertStringToDate.getDate(month);
//        sales.setMonthlySales(salesAnalysisService.getMonthSales(convertedDate));
//
//        //lấy số lượng đặt hàng
//        sales.setOrders(salesAnalysisService.getMonthOrder(convertedDate));
        return ResponseEntity.ok(sales);
    }

}
