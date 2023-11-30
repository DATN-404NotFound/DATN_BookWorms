package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.service.AccountService;
import com.poly.DATN_BookWorms.service.AddressService;
import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.service.DetailBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/myAccount")

public class MyAccountController {


    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    CRC32_SHA256 crc;
    @Autowired
    BookingService bookingService;
    @Autowired
    HttpServletRequest req;

    @Autowired
    CRC32_SHA256 crc32_SHA256;

    @Autowired
    SessionService sessionService;

    @Autowired
    AccountService accountService;

    @Autowired
    DetailBookingService detailBookingService;

    @Autowired
    AddressService addressService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping ("/myPersonal")
    public String  myPersonal(Model model){
        Account account = sessionService.get("user");
        model.addAttribute("account", account);
        System.out.println(account.toString());
        return "Client/My_account/MyPersonal";
    }

    @PostMapping("/changePassword/{username}")
    public String changePassword(Model model, @PathVariable String username,@RequestParam String OldPassword,
                                 @RequestParam String NewPassword,
                                 @RequestParam String confirmNewPassword) {
        Account user = sessionService.get("user");
        System.out.println(passwordEncoder.matches(OldPassword, user.getPassword()));
        if (passwordEncoder.matches(OldPassword, user.getPassword())){
//            if (!NewPassword.equals(confirmNewPassword) || confirmNewPassword!=null) {
//                model.addAttribute("messageChangePassword", "New Password and Confirm Password do not match");
//            model.addAttribute("messageChangePassword", "Error");
            if(NewPassword.equals(confirmNewPassword) || confirmNewPassword!=null) {
                user.setPassword(passwordEncoder.encode(NewPassword));
                accountService.update(user);
                model.addAttribute("notiChangePassword", "Password changed successfully");
                System.out.println("update1");
            }else if(!NewPassword.equals(confirmNewPassword) || confirmNewPassword==null){
                model.addAttribute("notiChangePassword", "Old password is incorrect");
            }
        }else if (!passwordEncoder.matches(OldPassword, user.getPassword())){
            model.addAttribute("notiChangePassword", "Old password is incorrect");
            System.out.println("update3");
        }
        return "forward:/myAccount/changePassword";
    }

    @PostMapping("/updateMypersonal/{username}")
    public String updateMypersonal(@Valid @ModelAttribute("account") Account accountUpdate, BindingResult bindingResult,
                                   @RequestParam("fileImage") Optional<MultipartFile> multipartFile)  throws Exception{
        Account user = sessionService.get("user");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (user != null) {
            if (bindingResult.hasErrors()){
                System.out.println("Error");
                return "redirect:/myAccount/myPersonal";
            }else if (!bindingResult.hasErrors()){
                if (accountUpdate.getFullname()!=null){
                    user.setFullname(accountUpdate.getFullname());
                }
                if (accountUpdate.getEmail()!=null) {
                    user.setEmail(accountUpdate.getEmail());
                }
                if (accountUpdate.getGender()!=null) {
                    user.setGender(accountUpdate.getGender());
                }
                if (accountUpdate.getAge()!=null) {
                    user.setAge(accountUpdate.getAge());
                }
                System.out.println(multipartFile);
                }if (!multipartFile.isEmpty()) {
                    MultipartFile file = multipartFile.get();
                    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                    System.out.println("1");
                    String uploadDir = "V:/FPT/DuAnTotNghiep/Source/DATN_BookWorms/src/main/resources/static/Client/images";
                    Path uploadPath = Paths.get(uploadDir);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    try {
                        InputStream inputStream = file.getInputStream();
                        Path filePath = uploadPath.resolve(fileName);
                        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                        //save change profile
                        user.setImage(fileName);
                    } catch (IOException e) {
                        throw new IOException("Could not  save uploaded file: " + fileName);
                    }
                }
                accountService.update(user);

        }
        return "redirect:/myAccount/myPersonal";
    }

//    @ModelAttribute("gender")
//    public Map<Boolean, String> getGender(){
//        Map<Boolean,String> map = new HashMap<>();
//        map.put(true,"Male");
//        map.put(false, "Female");
//        return map;
//    }

    @RequestMapping("/address")
    public String  address(Model model){

        Account account = sessionService.get("user");
        model.addAttribute("account", account);
        List<Addressusers> ad = addressService.findByUserId(account.getUserid());
        model.addAttribute("ad", ad);
        Addressusers addressusers = new Addressusers();
    	model.addAttribute("adr", addressusers);
        return "Client/My_account/Address";
    }

    
    @PostMapping("/newAddress")
    public String  newAddress(Model model,@ModelAttribute("adr") Addressusers addressSave,  @RequestParam String fullNameNewAddress,  @RequestParam String numberPhoneNewAddress,
    							@RequestParam String province, @RequestParam String district, 
    							@RequestParam String ward, @RequestParam String specificAddressNewAddress){
    	Account user = sessionService.get("user");
    	Addressusers addressusers = new Addressusers();
    	model.addAttribute("adr", addressusers);
    	if(user != null) {
    		
    		addressusers.setUserid(user.getUserid());
    		addressusers.setFullname(fullNameNewAddress);
    		addressusers.setPhonenumber(numberPhoneNewAddress);
    		System.out.println(specificAddressNewAddress + ", " + ward + ", " + district + ", " + province);
    		addressusers.setAddress(specificAddressNewAddress + ", " + ward + ", " + district + ", " + province);
    		addressusers.setStatusaddress(addressSave.getStatusaddress());
    		addressusers.setAddressuserid(crc.getCodeCRC32C(addressusers.toString()+ new Date()));
    		addressService.create(addressusers);
//    		System.out.println("adID: "+ addressService.generateNewAddressId());
    	}
    	return "redirect:/myAccount/address";
    }
    
    @RequestMapping("/editAddress/{addressuserid}")
    public String editAddress(@PathVariable String addressuserid) {
    	System.out.println("aaaaaaaa" + addressuserid);
    	if(addressuserid != null) {
    		
    	}
    	return "redirect:/myAccount/address";
    }
    
    @RequestMapping("/changePassword")
    public String  changePassword(Model model){
        Account account = sessionService.get("user");
        model.addAttribute("account", account);
        return "Client/My_account/ChangePassword";
    }

    @RequestMapping("/orderMyAccount")
    public String  orderMyAccount(Model model){
        Account account = sessionService.get("user");
        model.addAttribute("account", account);
        List<Bookings> booking = bookingService.findByUserId(account.getUserid());
        model.addAttribute("booking", booking);
//        List<Bookings> booking_StatusId = bookingService.findByStatusId(tab);
//        model.addAttribute("bs", booking_StatusId);
        return "Client/My_account/Order";
    }

    @RequestMapping("/orderDetailMyAccount/{id}")
    public String  orderDetailMyAccount(Model model, @PathVariable("id") String dbId){
        Account account = sessionService.get("user");
        model.addAttribute("account", account);
        List<Detailbookings> a = detailBookingService.findByBookingId(dbId);
        model.addAttribute("adr", a.get(0).bookings.listOfPayments.get(0));

        model.addAttribute("db", a);
        return "Client/My_account/OrderDetail";
    }
}
