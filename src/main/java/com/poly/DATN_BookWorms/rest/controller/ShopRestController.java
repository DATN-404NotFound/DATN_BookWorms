package com.poly.DATN_BookWorms.rest.controller;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.service.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class ShopRestController {
    @Autowired
    ShopService shopService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/detail")
    public ResponseEntity<Shoponlines> getShopDetail() {
        Account user = sessionService.get("user");
        Shoponlines shopDetail = shopService.findUserId(user.getUserid());
        return ResponseEntity.ok(shopDetail);
    }

    @PostMapping
    public ResponseEntity<Shoponlines> createUser(@RequestBody @Valid Shoponlines shoponlines) {
        // Lưu đối tượng người dùng vào cơ sở dữ liệu
        shopService.save(shoponlines);

        // Trả về phản hồi thành công
        return ResponseEntity.ok(shoponlines);
    }

    @PostMapping(value = "/save/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void saveImage(@RequestParam("fileImage") MultipartFile multipartFile, @RequestParam("shopId") String shopId ) throws Exception{
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String uploadDir = "D:/DATN/DATN_BookWorms/src/main/resources/static/SellerChannel/images/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try {
            InputStream inputStream  = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Could not  save uploaded file: " +fileName);
        }
    }
}
