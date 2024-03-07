package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.ImageBook;
import com.poly.DATN_BookWorms.repositories.ImagebooksRepo;
import com.poly.DATN_BookWorms.services.ImagesBookService;

@Service
public class IImagesBook implements ImagesBookService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	ImagebooksRepo imagebooksRepo;
	
	@Override
	public List<ImageBook> findByBookId(Long bookid) {
		// TODO Auto-generated method stub
		System.out.println("In ra "+ imagebooksRepo.findByBookid(bookid));
		logger.info("find list imagesBook with bookid : {}", bookid);
		return imagebooksRepo.findByBookid(bookid);
	}

	@Override
	public void save(ImageBook imageBook) {
		imagebooksRepo.save(imageBook);
	}

@Override
public void deleteImagebooksByBookId(Long bookId) {
	// Tìm tất cả Imagebooks có bookid tương ứng
	List<ImageBook> imageBookList = imagebooksRepo.findByBookid(bookId);

	// Xóa tất cả Imagebooks có bookid tương ứng
	imagebooksRepo.deleteAll(imageBookList);
}


}
