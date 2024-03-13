package com.poly.DATN_BookWorms.controllers;

import com.poly.DATN_BookWorms.services.AddressShopService;
import com.poly.DATN_BookWorms.services.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Ibook/seller")
public class SellerController {
    @Autowired
    ShopService shopService;
    @Autowired
    SessionService sessionService;

    @Autowired
    AddressShopService addressShopService;
    @RequestMapping("/index")
    public String seller(Model model){
        return "seller_template/index";
    }
    @RequestMapping("/homePageSeller")
    public String homePageSeller(Model model){
        return "seller_template/homePageSeller";
    }
    @RequestMapping("/orderManagement/sales")
    public String salesOrderManagement(Model model){
        return "seller_template/sales";
    }
    @RequestMapping("/shop/shopProfile")
    public String shopProfile(Model model){
        return "seller_template/profileShop";
    }

    @RequestMapping("/shop/shopProfile/change")
    public String changeProfile(Model model){
        return "seller_template/changeProfile";
    }

    @RequestMapping("/shop/setting/address")
    public String addressSetting(Model model){
        return "seller_template/addressSetting";
    }
    @RequestMapping("/shop/setting/shipping")
    public String shippingSetting(Model model){
        return "seller_template/shippingSetting";
    }
    @RequestMapping("/shop/setting/account")
    public String accountSetting(Model model){
        return "seller_template/profileNofication";
    }
    @RequestMapping("/shop/sales")
    public String sales(Model model){
        return "seller_template/dashboardSales";
    }
    @RequestMapping("/shop/finance/revenue")
    public String revenueFinance(Model model){
        return "seller_template/financeRevenue";
    }
    @RequestMapping("/shop/TranportChannel/CreateProduct")
    public String createProduct(Model model){
        return "seller_template/createProduct";
    }
    @RequestMapping("/shop/TranportChannel/EditBook")
    public String editProduct(Model model){
        return "seller_template/editBook";
    }
    @RequestMapping("/shop/TranportChannel/Transport")
    public String Tranport(Model model){
        return "seller_template/transportChannel";
    }
    @RequestMapping("/shop/TranportChannel/Voucher")
    public String Voucher(Model model){
        return "seller_template/voucher";
    }
    @RequestMapping("/shop/TranportChannel/CreateVoucher")
    public String createVoucher(Model model){
        return "seller_template/createVoucher";
    }
    @RequestMapping("/shop/TranportChannel/ShopRewiew")
    public String rewiews(Model model){
        return "seller_template/shopRewiew";
    }
    @RequestMapping("/shop/finance/bankAccountBalance")
    public String bankAccountBalance(Model model){
        return "seller_template/bankAccountBalance";
    }
    @RequestMapping("/shop/finance/createBankAccount")
    public String createBankAccount(Model model){
        return "seller_template/createBankAccount";
    }
    @RequestMapping("/shop/bulkDelivery")
    public String bulkDelivery(Model model){
        return "seller_template/bulkDelivery";
    }
}
