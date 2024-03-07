package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.AddressUser;
import com.poly.DATN_BookWorms.services.AccountAddressService;
import com.poly.DATN_BookWorms.services.AddressService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/address")
public class AccountAddressRestController {

	@Autowired
	CRC32_SHA256 crc32_SHA256;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	AccountAddressService accountAddressService;
	
	@Autowired
    AddressService addressService;

	@GetMapping
	public List<AddressUser> getAddressByUser(){
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		System.out.println("in ra "+accountAddressService.getAdressByUser(userid) );
		return accountAddressService.getAdressByUser(userid);
	}
	
	@GetMapping("/{id}")
	public AddressUser getAddressById(@PathVariable String id ) {
		return addressService.byAddressUserId(id);
	}
	

	@PostMapping
	public AddressUser postAddress(@RequestBody AddressUser json){
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		List<AddressUser> as = accountAddressService.getAdressByUser(userid);
		if(json.getStatusaddress().equals("Mặc định")) {
			for (AddressUser addressusers : as) {
				if(addressusers.getStatusaddress().equals("Mặc định")) {
					addressusers.setStatusaddress("Không");
					accountAddressService.save(addressusers);
				}
			}
		}
		return addressService.update(json);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable String id ){ 
		
		addressService.delete(id);
	}

	@PutMapping("/update")
	public AddressUser ok(@RequestBody String id){
		AddressUser add = accountAddressService.findById(id);

		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		List<AddressUser> as = accountAddressService.getAdressByUser(userid);
		for (AddressUser addressusers : as) {
			addressusers.setStatusaddress("Không");
			accountAddressService.save(addressusers);
		}
		add.setStatusaddress("Mặc định");
		accountAddressService.save(add);
		return add ;
	}
}
