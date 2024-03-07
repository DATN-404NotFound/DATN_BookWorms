package com.poly.DATN_BookWorms.restControllers;

import java.util.Date;
import java.util.List;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.HasSale;
import com.poly.DATN_BookWorms.services.HasSaleService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.DATN_BookWorms.entities.Sale;
import com.poly.DATN_BookWorms.services.SaleService;

@RestController
@CrossOrigin("")
@RequestMapping("/rest/sale")
public class SaleRestController {
    @Autowired
    SessionService service;
    @Autowired
    SaleService saleService;
    @Autowired
    HasSaleService hasSaleService;

    @GetMapping("/")
    public List<Sale> getSale() {
        return saleService.findAll();
    }

    @GetMapping("/{coupon}")
    public Sale getSaleCounpon(@PathVariable("coupon") String coupon) {
        return saleService.findById(coupon);
    }

    @GetMapping("/getListHasSales")
    public ResponseEntity<List<HasSale>> getListHasSales() {
        return ResponseEntity.ok(hasSaleService.findAll());
    }

    @PostMapping("/getHasSaleFromBook")
    public ResponseEntity<Integer> getHasSaleFromBoook(@RequestParam("bookid") String bookId, @RequestParam("saleid") String saleId) {
        Integer hasSalesId = hasSaleService.findHasSaleIdByBookId(Integer.parseInt(bookId), saleId);
        if (hasSalesId == null)
            return ResponseEntity.ok(null);
        else
            return ResponseEntity.ok(hasSalesId);
    }

    @GetMapping("/{intendfor}")
    public List<Sale> getSaleShopOfIntend(@PathVariable("intendfor") String intendfor) {
        return saleService.saleOfShopIntendFor(intendfor);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> createSales(@RequestBody Sale sale) {
        Sale createdSale = saleService.create(sale);
        return ResponseEntity.ok(createdSale);
    }

    @GetMapping("/listvoucher/{intendforv}")
    public List<Sale> getAllVoucher(@PathVariable("intendforv") String intendfor) {
        Account account = service.get("user");
        return saleService.findAllByShopid(intendfor, account.getListOfShopOnline().get(0).getShopid());
    }

    @GetMapping("/findByCouponCode/{couponCode}")
    public List<HasSale> findAllByCouponCode(@PathVariable("couponCode") String couponCode) {
        return hasSaleService.findAllByCouponCode(couponCode);
    }

    @PostMapping(value = "/createHassale/{bookID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HasSale> createHassales(@RequestBody HasSale hasSale, @PathVariable("bookID") Integer bookID) {

        HasSale createdHassale = new HasSale();
        createdHassale.setBookid(bookID);
        createdHassale.setSaleid(hasSale.getSaleid());
        createdHassale.setStarttime(new Date());
        createdHassale.setEndtime(hasSale.getEndtime());
        hasSaleService.saveHassales(createdHassale);
        return ResponseEntity.ok(createdHassale);
    }

    @DeleteMapping("/deleteHassale/{hassaleId}")
    public ResponseEntity<String> deleteHassalesById(@PathVariable Integer hassaleId) {
        try {
            hasSaleService.deleteHassalesById(hassaleId);
            return new ResponseEntity<>("Hassales deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting Hassales: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllBySaleId/{saleId}")
    public ResponseEntity<List<HasSale>> findAllBySaleId(@PathVariable String saleId) {
        List<HasSale> hasSaleList = hasSaleService.findAllBysaleid(saleId);
        if (!hasSaleList.isEmpty()) {
            return new ResponseEntity<>(hasSaleList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
