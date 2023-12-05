package com.poly.DATN_BookWorms.rest.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.poly.DATN_BookWorms.config.PaymentConfig;
import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.PaymentShopRepo;
import com.poly.DATN_BookWorms.repo.ShoponlinesRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/payment")
public class AdminPaymentRestController {

	@Autowired
	PaymentShopRepo paymentShopRepo;
	@Autowired
	ShoponlinesRepo shopOnlinesRepo;

	@GetMapping("/payment-callback")
	public void paymentCallback(@RequestParam Map<String, String> queryParams, HttpServletResponse response)
			throws IOException, NumberFormatException, NotFoundException {
		String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
		String paymentShopId = queryParams.get("paymentshopid");
		if (paymentShopId != null && !paymentShopId.equals("")) {
			if ("00".equals(vnp_ResponseCode)) {
				// Giao dịch thành công
				// Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
				PaymentShop paymentShop = paymentShopRepo.findById(Integer.parseInt(queryParams.get("paymentshopid")))
						.orElseThrow(() -> new NotFoundException());
				paymentShop.setStatus(true);
				Shoponlines shopOnlines = paymentShopRepo.findShopId(paymentShop.getShoponlines().getShopid());

				System.out.println("hi " + paymentShop.getShoponlines().getTotal());
				shopOnlines.setTotal(shopOnlines.getTotal() - paymentShop.getValuepayment());
				shopOnlinesRepo.save(shopOnlines);
				paymentShopRepo.save(paymentShop);
				response.sendRedirect("http://localhost:8080/api/payment/callpayment");
			} else {
				// Giao dịch thất bại
				// Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\
				response.sendRedirect("http://localhost:4200/payment-failed");

			}
		}
	}

	@GetMapping("/create_payment/{paymentshopid}&{valuepayment}")
	public RedirectView createPayment(@PathVariable("paymentshopid") long paymentshopid,
			@PathVariable("valuepayment") long valuepayment, HttpServletRequest request)
			throws UnsupportedEncodingException {

		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "other";

		long amount = valuepayment * 100;
		String bankCode = "NCB";

		String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
		String vnp_IpAddr = "127.0.0.1";

		String vnp_TmnCode = PaymentConfig.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");

		vnp_Params.put("vnp_BankCode", bankCode);
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_OrderType", orderType);

		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_ReturnUrl + "?paymentshopid=" + paymentshopid);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(paymentUrl);

		return redirectView;
	}
}
