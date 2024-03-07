package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.ImageBook;
import com.poly.DATN_BookWorms.services.serviceImplements.IImagesBook;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/imagebook")
public class ImagesBookRestController {
	
	@Autowired
    IImagesBook IImagesBook;
	
	@GetMapping("/{bookid}")
	public List<ImageBook> getImageBookByBookid(@PathVariable("bookid") Long bookid){
		return  IImagesBook.findByBookId(bookid);
	}
	
}
