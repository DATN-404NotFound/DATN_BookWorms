package com.poly.DATN_BookWorms.rest.controller;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.service.*;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/evaluates")
public class EvaluateRestController {
    @Autowired
    EvaluateService evaluateService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ShopOnlinesService shopOnlinesService;
    @Autowired
    DetailBookingService detailBookingService;
    @Autowired
    ImageEvaluatesService imageEvaluatesService;
    @GetMapping
    public List<Evaluates> findAll() {
        Account account = sessionService.get("user");
        Shoponlines shoponlines = shopOnlinesService.findShoponlinesByUserId(account.getUserid());
        System.out.println(shoponlines.getShopid());
        return evaluateService.findEvaluatesByShopId(shoponlines.getShopid());
    }
    
    @PostMapping("/save")
	public Evaluates postEvaluates(@RequestBody Evaluates evaluateData) { 
    	System.out.println("success 2222222");
    	System.out.println("1234"+ evaluateData.getDbid());
    	Detailbookings d = detailBookingService.dtb(evaluateData.getDbid());
    	if(d.bookings.orderstatusid != 8) {
    		System.out.println("tt" + d.bookings.getOrderstatusid());
    		d.bookings.setOrderstatusid(8);
    	}
		try {
			
			return evaluateService.create(evaluateData);
		} catch (Exception e) {
			System.out.println("errr"+ e);
			return null;
			// TODO: handle exception
		}
	}
    @PostMapping(value = "/uploadImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> handleFileUploadForm(
            @RequestPart("file") MultipartFile file,
            @RequestParam("evaluateid") String evaluateId) throws IOException {
        try {
            File f = new ClassPathResource("").getFile();
            String uploadDir = "V:/FPT/DuAnTotNghiep/Source/DATN_BookWorms/src/main/resources/static/SellerChannel/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!java.nio.file.Files.exists(uploadPath)) java.nio.file.Files.createDirectories(uploadPath);

            String uniqueFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(uniqueFileName)
                    .toUriString();

            var result = Map.of(
                    "filename", uniqueFileName,
                    "fileUri", fileUri
            );
            Imageevaluates imageevaluates = new Imageevaluates();
            imageevaluates.setImage(uniqueFileName);
            imageevaluates.setEvaluateid(Integer.valueOf(evaluateId));
            imageevaluates.setType("image");
            imageEvaluatesService.save(imageevaluates);
            return ResponseEntity.ok().body(result);

        } catch (IOException e) {
        	e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
