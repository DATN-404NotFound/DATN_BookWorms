package com.poly.DATN_BookWorms.restControllers;

import com.poly.DATN_BookWorms.entities.ShopOnline;
import com.poly.DATN_BookWorms.repositories.ShoponlinesRepo;
import com.poly.DATN_BookWorms.responses.AnalysisFinanceResponse;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.entities.PaymentAccount;
import com.poly.DATN_BookWorms.services.PaymentAccountService;
import com.poly.DATN_BookWorms.services.PaymentShopService;
import com.poly.DATN_BookWorms.services.ShopService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.SessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    PaymentAccountService paymentAccountService;
    @Autowired
    CRC32_SHA256 crc32Sha256;
    @GetMapping("/getRevenue")
    public ResponseEntity<ShopOnline> getRevenue() {
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        return ResponseEntity.ok(shoponline);
    }
    @GetMapping("/getListFinance")
    public ResponseEntity<List<PaymentShop>> getListFinance() {
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        List<PaymentShop> listFinance = paymentShopService.findByShopId(shoponline.getShopid());
        return ResponseEntity.ok(listFinance);
    }
    @GetMapping("/getAnalysisFinance")
    public ResponseEntity<AnalysisFinanceResponse> getAnalysisFinance() {
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
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
        float monthPaid = paymentShopService.getMonthPaid(shoponline.getShopid(),startDate,endDate,true);
        float totalPaid = paymentShopService.getTotal(shoponline.getShopid(),true);
        float totalUnPaid = paymentShopService.getTotal(shoponline.getShopid(),false);
        AnalysisFinanceResponse analysisFinance = new AnalysisFinanceResponse(monthPaid,totalPaid,totalUnPaid);
        return ResponseEntity.ok(analysisFinance);
    }
    @PostMapping(value = "/sendRequestPayment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PaymentShop> createUser(@RequestParam("paymentTotal") String paymentTotal) {
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        // Lưu đối tượng người dùng vào cơ sở dữ liệu
        PaymentShop  paymentShop = new PaymentShop();
        paymentShop.setCreateat(new Date());
        paymentShop.setStatus(false);
        paymentShop.setValuepayment(Long.parseLong(paymentTotal));
        paymentShop.setShopid(shoponline.getShopid());
        paymentShop.setIsdelete(false);
        System.out.println(shoponline.toString());
        paymentShopService.save(paymentShop);
        // Trả về phản hồi thành công
        return ResponseEntity.ok(paymentShop);
    }
    @GetMapping("/getAccountBalance")
    public ResponseEntity<PaymentAccount> getAccountBalance() {
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        PaymentAccount accountBalance = new PaymentAccount();
        if (shoponline.getPaycount() != null){
           accountBalance = paymentAccountService.findById(shoponline.getPaycount());
        }
        return ResponseEntity.ok(accountBalance);
    }

    @PostMapping(value = "/saveAccountBalance" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentAccount> saveAccountBalance(@RequestBody   @Valid PaymentAccount paymentaccount) {
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        String paymentAccountId = crc32Sha256.getCodeCRC32C(paymentaccount.getAccountnumber()+paymentaccount.getCccd()) ;

        paymentaccount.setPaid(paymentAccountId);
        paymentAccountService.save(paymentaccount);
        shoponline.setPaycount(paymentAccountId);

        shopService.save(shoponline);
        return ResponseEntity.ok(paymentaccount);
    }
}
