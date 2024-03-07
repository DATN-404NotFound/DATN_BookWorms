package com.poly.DATN_BookWorms.controllers.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.AddressUser;
import com.poly.DATN_BookWorms.entities.DiscountCode;
import com.poly.DATN_BookWorms.entities.PaymentAccount;
import com.poly.DATN_BookWorms.entities.ShippingUnit;
import com.poly.DATN_BookWorms.services.AccountAddressService;
import com.poly.DATN_BookWorms.services.CartService;
import com.poly.DATN_BookWorms.services.DiscountCodeService;
import com.poly.DATN_BookWorms.services.PaymentAccountService;
import com.poly.DATN_BookWorms.services.SaleService;
import com.poly.DATN_BookWorms.services.ShippingUnitService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	CartService cartService;

	@Autowired
	SaleService saleService;

	@Autowired
	CRC32_SHA256 crc32_SHA256;

	@Autowired
	HttpServletRequest request;

	@Autowired
	AccountAddressService accountAddressService;

	@Autowired
	DiscountCodeService discountCodeService;

	@Autowired
	ShippingUnitService shippingUnitService;
	
	@Autowired
	PaymentAccountService paymentAccountService;

	@GetMapping
	public String orderPage(Model model) {
		try {
			String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
			List<AddressUser> addressusser = accountAddressService.getAdressByUser(userid);
			model.addAttribute("addressusser", addressusser);
			List<DiscountCode> discountcode = discountCodeService.findDisountForSys(userid);
			model.addAttribute("discountcode", discountcode);
			List<ShippingUnit> shippingunit = shippingUnitService.getAll();
			model.addAttribute("shippingunit", shippingunit);
			List<PaymentAccount> paymentaccount = paymentAccountService.findWithUser(userid);
			model.addAttribute("paymentaccount", paymentaccount);
			logger.info("get order Page");
		} catch (Exception e) {
			logger.info("Error during controller with error :{}",e);
		}
		return "Client/cart_client/deal";
	}
	
	@PostMapping("/payment")
	public String OrderPayment(Model model) {
		return "redirect:/cart";
	}

	@GetMapping("/success")
	public String success(Model model){ 
		return "Client/cart_client/success";
	}
}
