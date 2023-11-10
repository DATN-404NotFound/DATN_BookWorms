package com.poly.DATN_BookWorms.rest.controller;

import com.poly.DATN_BookWorms.beans.Sales;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.service.SalesAnalysisService;
import com.poly.DATN_BookWorms.utils.ConvertStringToDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/salesAnalysis")
public class SalesAnalysisRestController {

    @Autowired
    SalesAnalysisService salesAnalysisService;
    @Autowired
    ConvertStringToDate convertStringToDate;

    @PostMapping(value = "/getSalesAnalysis", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sales> getSalesAnalysis(@RequestParam("month") String month,@RequestParam("year") String year ) {
        Sales sales = new Sales();
//        // lấy doanh số bán hàng theo tháng
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));

        // Lấy ngày đầu tháng
        LocalDate ngayDauThang = yearMonth.atDay(1);

        // Lấy ngày cuối cùng của tháng
        LocalDate ngayCuoiThang = yearMonth.atEndOfMonth();

        // Chuyển đổi LocalDate sang Date
        Date startDate = Date.from(ngayDauThang.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

       // Date dateNgayCuoiThang
        Date endDate = Date.from(ngayCuoiThang.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        //lấy doanh số theo tháng năm
        sales.setMonthlySales(salesAnalysisService.getMonthSales(startDate,endDate));
//
//        //lấy số lượng đặt hàng
//             sales.setOrders(salesAnalysisService.getMonthOrder(convertedDate));
        return ResponseEntity.ok(sales);
    }

}
