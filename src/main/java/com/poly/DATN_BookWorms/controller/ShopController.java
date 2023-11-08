package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Evaluates;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.response.BookResponse;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.service.EvaluatesService;
import com.poly.DATN_BookWorms.service.ShopOnlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopOnlinesService shopOnlinesService;
    @Autowired
    BookService bookService;
    @Autowired
    EvaluatesService evaluatesService;

    @GetMapping("/{id}")
    public String profile(Model model, @PathVariable("id") Integer id,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Shoponlines list = shopOnlinesService.findById(id);
        Integer total = evaluatesService.sumDbidByEvaluateId(id);
        model.addAttribute("total", total);
        model.addAttribute("profile", list);
        Page<Books> bookPage = bookService.findByshopid(id,pageable);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        List<Books> minquantity  = bookService.findTop5LowestQuantityBooksByShopId(id);
        model.addAttribute("minquantity", minquantity);
        return "Client/Product_page/product_shop_list";
    }
}