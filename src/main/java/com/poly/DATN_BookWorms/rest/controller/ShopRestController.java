package com.poly.DATN_BookWorms.rest.controller;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.ShoponlinesRepo;
import com.poly.DATN_BookWorms.service.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class ShopRestController {
    @Autowired
    ShopService shopService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/detail")
    public ResponseEntity<Shoponlines> getShopDetail() {
        Account user = sessionService.get("user");
        Shoponlines shopDetail = shopService.findUserId(user.getUserid());
        return ResponseEntity.ok(shopDetail);
    }

    @PostMapping
    public ResponseEntity<Shoponlines> createUser(@RequestBody @Valid Shoponlines shoponlines) {
        // Lưu đối tượng người dùng vào cơ sở dữ liệu
        shopService.save(shoponlines);

        // Trả về phản hồi thành công
        return ResponseEntity.ok(shoponlines);
    }
}
