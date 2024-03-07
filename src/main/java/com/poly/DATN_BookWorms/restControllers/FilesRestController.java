package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.File;
import com.poly.DATN_BookWorms.services.FileShopService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/files")
public class FilesRestController {

	@Autowired
	FileShopService fileShopService;

	@GetMapping("/{id}")
	public List<File> getFileByShop(@PathVariable("id") Integer shopid) {
		return fileShopService.getFileByShop(shopid);
	}

}
