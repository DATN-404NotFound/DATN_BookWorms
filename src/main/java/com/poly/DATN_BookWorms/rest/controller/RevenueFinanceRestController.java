package com.poly.DATN_BookWorms.rest.controller;

import com.poly.DATN_BookWorms.beans.AnalysisFinance;
import com.poly.DATN_BookWorms.beans.CategoryRanking;
import com.poly.DATN_BookWorms.beans.Sales;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.service.PaymentShopService;
import com.poly.DATN_BookWorms.service.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/revenueFinance")
public class RevenueFinanceRestController {

    @Autowired
    SessionService sessionService;

    @Autowired
    ShopService shopService;

    @Autowired
    PaymentShopService paymentShopService;
    @GetMapping("/getRevenue")
    public ResponseEntity<Shoponlines> getRevenue() {
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        return ResponseEntity.ok(shoponlines);
    }
    @GetMapping("/getListFinance")
    public ResponseEntity<List<PaymentShop>> getListFinance() {
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        List<PaymentShop> listFinance = paymentShopService.findByShopId(shoponlines.getShopid());
        return ResponseEntity.ok(listFinance);
    }
    @GetMapping("/getAnalysisFinance")
    public ResponseEntity<AnalysisFinance> getAnalysisFinance() {
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        //get starts and endtime Month now
        YearMonth yearMonth = YearMonth.now();
        int monthNumber = yearMonth.getMonthValue();

        // Lấy ngày đầu tháng
        LocalDate ngayDauThang = yearMonth.atDay(1);

        // Lấy ngày cuối cùng của tháng
        LocalDate ngayCuoiThang = yearMonth.atEndOfMonth();

        // Chuyển đổi LocalDate sang Date
        Date startDate = Date.from(ngayDauThang.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Date dateNgayCuoiThang
        Date endDate = Date.from(ngayCuoiThang.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        //
        float monthPaid = paymentShopService.getMonthPaid(shoponlines.getShopid(),startDate,endDate,true);
        float totalPaid = paymentShopService.getTotal(shoponlines.getShopid(),true);
        float totalUnPaid = paymentShopService.getTotal(shoponlines.getShopid(),false);
        AnalysisFinance analysisFinance = new AnalysisFinance(monthPaid,totalPaid,totalUnPaid);
        return ResponseEntity.ok(analysisFinance);
    }
    @PostMapping(value = "/sendRequestPayment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentShop> createUser(@RequestParam("paymentTotal") String paymentTotal) {
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        // Lưu đối tượng người dùng vào cơ sở dữ liệu
        PaymentShop  paymentShop =  new PaymentShop();
        paymentShop.setCreateat(new Date());
        paymentShop.setStatus(false);
        paymentShop.setValuepayment(Double.parseDouble(paymentTotal));
        paymentShop.setShoponlines(shoponlines);
        paymentShopService.save(paymentShop);
        // Trả về phản hồi thành công
        return ResponseEntity.ok(paymentShop);
    }
}
