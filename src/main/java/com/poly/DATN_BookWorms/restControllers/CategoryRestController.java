package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Category;
import com.poly.DATN_BookWorms.services.TypeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.poly.DATN_BookWorms.services.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    TypeBookService typeBookService;

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @PostMapping("/cateWithBook")
    public List<Category> getCateWithBookId(@RequestParam("bookid") String bookId) {
        return typeBookService.findCateByBookId(Integer.parseInt(bookId));
    }

}
