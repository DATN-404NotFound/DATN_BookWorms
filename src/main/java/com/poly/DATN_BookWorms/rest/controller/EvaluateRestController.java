package com.poly.DATN_BookWorms.rest.controller;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Evaluates;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.service.EvaluateService;
import com.poly.DATN_BookWorms.service.ShopOnlinesService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/evaluates")
public class EvaluateRestController {
    @Autowired
    EvaluateService evaluateService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ShopOnlinesService shopOnlinesService;
    @GetMapping
    public List<Evaluates> findAll() {
        Account account = sessionService.get("user");
        Shoponlines shoponlines = shopOnlinesService.findShoponlinesByUserId(account.getUserid());
        System.out.println(shoponlines.getShopid());
        return evaluateService.findEvaluatesByShopId(shoponlines.getShopid());
    }

}
