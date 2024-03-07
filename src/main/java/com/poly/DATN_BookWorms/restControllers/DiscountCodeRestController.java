package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.DiscountCode;
import com.poly.DATN_BookWorms.services.DiscountCodeService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/discount")
public class DiscountCodeRestController {

	@Autowired
	DiscountCodeService discountCodeService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CRC32_SHA256 crc32_SHA256;
	
	@PostMapping("")
	public DiscountCode getDiscountCodeBySaleid(@RequestBody DiscountCode discountCode) {
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		System.out.println("in d : "+ discountCode.toString());
		System.out.println("in : "+   discountCodeService.findSalesId(discountCode.saleid, userid));
		DiscountCode dis = discountCodeService.findSalesId(discountCode.saleid, userid);
		if(dis != null) { 
			return dis;
		}
		else { 
			discountCode.userid = userid;
			return discountCodeService.create(discountCode);
		
		}
		
	}
	
	
	@GetMapping("/{saleid}")
	public DiscountCode createDisscount(@PathVariable("saleid") String saleid) {
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		System.out.println("in : "+   discountCodeService.findSalesId(saleid, userid));
		return discountCodeService.findSalesId(saleid, userid);
	}
	
	

	@GetMapping("/findbyUser")
	public List<DiscountCode> findbyUser() {
		
		return discountCodeService.findDisountByUserId(crc32_SHA256.getCodeCRC32C(request.getRemoteUser()));
	}
	
	@GetMapping
	public List<DiscountCode> findAll(){
		return  discountCodeService.findAll();
	}
	
	@DeleteMapping("/{dis}")
	public void delete(@PathVariable("dis") Integer dis) { 
		 discountCodeService.delete(dis);
	}
	
	@DeleteMapping("/dele/{sa}")
	public void deleteSa(@PathVariable("sa") String sa) {
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		DiscountCode dis = discountCodeService.findSalesId(sa, userid);
		 discountCodeService.delete(dis.discountcodeid);
	}
	
	@GetMapping("/view/{discountcodeid}")
	public DiscountCode getBookingId(@PathVariable Integer discountcodeid ) {
		System.out.println("discountcodeid: " + discountcodeid);
//		Discountcodes d = discountCodeService.findById(discountcodeid);
//		System.out.println(d.sales.promotionname);
		return discountCodeService.findById(discountcodeid);
	}
}
