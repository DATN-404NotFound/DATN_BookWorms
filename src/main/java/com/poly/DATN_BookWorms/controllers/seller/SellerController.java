package com.poly.DATN_BookWorms.controllers.seller;

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
        return "SellerChannel/index";
    }
    @RequestMapping("/homePageSeller")
    public String homePageSeller(Model model){
        return "SellerChannel/homePageSeller";
    }
    @RequestMapping("/orderManagement/sales")
    public String salesOrderManagement(Model model){
        return "sales";
    }
    @RequestMapping("/shop/shopProfile")
    public String shopProfile(Model model){
        return "profileShop";
    }

    @RequestMapping("/shop/shopProfile/change")
    public String changeProfile(Model model){
        return "changeProfile";
    }

    @RequestMapping("/shop/setting/address")
    public String addressSetting(Model model){
        return "addressSetting";
    }
    @RequestMapping("/shop/setting/shipping")
    public String shippingSetting(Model model){
        return "shippingSetting";
    }
    @RequestMapping("/shop/setting/account")
    public String accountSetting(Model model){
        return "profileNofication";
    }
    @RequestMapping("/shop/sales")
    public String sales(Model model){
        return "dashboardSales";
    }
    @RequestMapping("/shop/finance/revenue")
    public String revenueFinance(Model model){
        return "financeRevenue";
    }
    @RequestMapping("/shop/TranportChannel/CreateProduct")
    public String createProduct(Model model){
        return "SellerChannel/createProduct";
    }
    @RequestMapping("/shop/TranportChannel/EditBook")
    public String editProduct(Model model){
        return "editBook";
    }
    @RequestMapping("/shop/TranportChannel/Transport")
    public String Tranport(Model model){
        return "transportChannel";
    }
    @RequestMapping("/shop/TranportChannel/Voucher")
    public String Voucher(Model model){
        return "SellerChannel/voucher";
    }
    @RequestMapping("/shop/TranportChannel/CreateVoucher")
    public String createVoucher(Model model){
        return "SellerChannel/createVoucher";
    }
    @RequestMapping("/shop/TranportChannel/ShopRewiew")
    public String rewiews(Model model){
        return "shopRewiew";
    }
    @RequestMapping("/shop/finance/bankAccountBalance")
    public String bankAccountBalance(Model model){
        return "bankAccountBalance";
    }
    @RequestMapping("/shop/finance/createBankAccount")
    public String createBankAccount(Model model){
        return "createBankAccount";
    }
    @RequestMapping("/shop/bulkDelivery")
    public String bulkDelivery(Model model){
        return "SellerChannel/bulkDelivery";
    }
}
