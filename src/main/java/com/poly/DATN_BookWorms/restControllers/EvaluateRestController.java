package com.poly.DATN_BookWorms.restControllers;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.services.*;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public List<Evaluate> findAll() {
        Account account = sessionService.get("user");
        ShopOnline shoponline = shopOnlinesService.findShoponlinesByUserId(account.getUserid());
        System.out.println(shoponline.getShopid());
        return evaluateService.findEvaluatesByShopId(shoponline.getShopid());
    }
    
    @PostMapping("/save")
	public Evaluate postEvaluates(@RequestBody Evaluate evaluateData) {
    	System.out.println("success 2222222");
    	System.out.println("1234"+ evaluateData.getDbid());
    	DetailBooking d = detailBookingService.dtb(evaluateData.getDbid());
    	if(d.booking.orderstatusid != 8) {
    		System.out.println("tt" + d.booking.getOrderstatusid());
    		d.booking.setOrderstatusid(8);
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
            String uploadDir = "./src/main/resources/static/SellerChannel/images/";
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
            ImageEvaluate imageEvaluate = new ImageEvaluate();
            imageEvaluate.setImage(uniqueFileName);
            imageEvaluate.setEvaluateid(Integer.valueOf(evaluateId));
            imageEvaluate.setType("image");
            imageEvaluatesService.save(imageEvaluate);
            return ResponseEntity.ok().body(result);

        } catch (IOException e) {
        	e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
