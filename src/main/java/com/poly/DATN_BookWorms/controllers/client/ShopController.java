package com.poly.DATN_BookWorms.controllers.client;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.services.BookService;
import com.poly.DATN_BookWorms.services.DiscountCodeService;
import com.poly.DATN_BookWorms.services.EvaluatesService;
import com.poly.DATN_BookWorms.services.SaleService;
import com.poly.DATN_BookWorms.services.ShopOnlinesService;
import com.poly.DATN_BookWorms.services.TypeBookService;
import com.poly.DATN_BookWorms.services.WriterService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

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
	TypeBookService typeBookService;
	
	@Autowired
	WriterService writerService;
	
    @Autowired
    BookService bookService;
    @Autowired
    EvaluatesService evaluatesService;
    
    @Autowired
    SaleService saleService;
    
    @Autowired
    DiscountCodeService discountCodeService;

    @Autowired
    HttpServletRequest req;
    
    @Autowired
    CRC32_SHA256 crc32_SHA256;
    
    
   
    
    @GetMapping("/{id}")
    public String profile(Model model, @PathVariable("id") Integer id,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Integer total = evaluatesService.sumDbidByEvaluateId(id);
        model.addAttribute("total", total);

        ShopOnline list = shopOnlinesService.findById(id);
        model.addAttribute("profile", list);

        Page<Book> bookPage = bookService.findByshopid(id,pageable);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());

        List<WritingMaster> listWriter = writerService.getWrittingWithSHop(id);
        model.addAttribute("listWriter", listWriter);

        List<Category> listCateogories = typeBookService.getCategoriesWithShop(id);
        model.addAttribute("listCateogories", listCateogories);

        List<PublishingCompany> plcm = bookService.getPCWithShop(id);
        model.addAttribute("plcm", plcm);
        
        if(req.getRemoteUser() == null) { 
            List<Sale> saleByShopAndByIntendFor = null;
            model.addAttribute("listSaleOfshop", saleByShopAndByIntendFor);

            List<DiscountCode> findDisountOfShopWithUser =null;
            model.addAttribute("dis", findDisountOfShopWithUser);
        }
        else { 
            List<Sale> saleByShopAndByIntendFor = saleService.saleByShopAndByIntendFor(id, "D");
            model.addAttribute("listSaleOfshop", saleByShopAndByIntendFor);

            List<DiscountCode> findDisountOfShopWithUser = discountCodeService.findDisountOfShopWithUser( "D", crc32_SHA256.getCodeCRC32C(req.getRemoteUser()), id);
            model.addAttribute("dis", findDisountOfShopWithUser);
        }
        
        return "Client/Product_page/product_shop_list";
    }
}
