package com.poly.DATN_BookWorms.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repo.ImagebooksRepo;
import com.poly.DATN_BookWorms.repo.TypebooksRepo;
import com.poly.DATN_BookWorms.response.BookResponse;
import com.poly.DATN_BookWorms.utils.FileUploadUtil;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.service.BookService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookServiceImp implements BookService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	BooksRepo bookRepo;
	@Autowired
	ImagebooksRepo imagebooksRepo;
	@Autowired
	TypebooksRepo typebooksRepo;
	@Autowired
	SessionService sessionService;

	@Override
	public List<Books> findAll() {
		// TODO Auto-generated method stub
		logger.info("get all books : {}", bookRepo.findAll());
		return bookRepo.findAll();
	}

	@Override
	public Books findById(Long id) {
		// TODO Auto-generated method stub
		logger.info("get book by id have id : {} abd return  : {}",bookRepo.findById(id).get());
		return bookRepo.findById(id).get();
	}

	@Override
	public Page<Books> findAll(Pageable pageable) {
		logger.info("get all books  return pageable : {}", bookRepo.findAll(pageable));
		return bookRepo.findAll(pageable);
	}


//	@Override
//	public List<Books> findByCategoryId(String cid) {
//		return bookRepo.findByCategoryId(cid);
//	}

	@Override
	public Books creates(String bookname, String language, String size, Double weight, Integer totalpage,
						 Integer publishingyear, Double price, Integer quantity,
						 Integer publishingcompanyid, Boolean isactive, MultipartFile[] images, Integer category) {
		Account user = sessionService.get("user");
			Books book = new Books();
			book.setBookname(bookname);
			book.setLanguage(language);
			book.setSize(size);
			book.setWeight(weight);
			book.setTotalpage(totalpage);
			book.setPublishingyear(publishingyear);
			book.setPrice(price);
			book.setQuantity(quantity);
			book.setStatues("Còn hàng");
			book.setPublishingcompanyid(publishingcompanyid);
			book.setIsactive(isactive);
			book.setQuantitysold(0);
			book.setShopid(user.getListOfShoponlines().get(0).getShopid());
		bookRepo.save(book);


		System.out.println(book);
		Typebooks typebooks = new Typebooks();
		typebooks.setBookid(book.getBookid().intValue());
		typebooksRepo.save(typebooks);
		for (MultipartFile image : images) {
			try {
				String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
				String uploadDir = "D:/Work/DATN_BookWorms/src/main/resources/static/Client/images";
				FileUploadUtil.saveFile(uploadDir, fileName, image);
				Imagebooks imagebooks = new Imagebooks();
				imagebooks.setBookid(book.getBookid().intValue());
				imagebooks.setName(fileName);
				imagebooks.setTypefile("image");

				imagebooksRepo.save(imagebooks);
			} catch (IOException e) {
				System.out.println("not save image");
				e.printStackTrace();
			}
		}

		return book;



	}

	@Override
	public Books update(Books book) {
		// TODO Auto-generated method stub
		logger.info("update book with book : {}", book);
		return bookRepo.save(book);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		logger.info("delete book with id : {}", id);
		bookRepo.deleteById(id);
	}

	@Override
	public List<Books> getBooksByCategoryID(Integer categoryID) {
		logger.info("getBooksByCategoryID with categoryID : {}", categoryID);
		return bookRepo.findBooksByCategoryID(categoryID);
	}
	@Override
	public Page<BookResponse> findAllBook(Pageable pageable) {
		logger.info("findAllBook  with pageable : {}", pageable);
		return bookRepo.findAllBook(pageable);
	}

	@Override
	public Books findTopBookByQuantitySold() {
		return bookRepo.findFirstByOrderByQuantitysoldDesc();
	}

	@Override
	public Page<Books> findByshopid(Integer shopid, Pageable pageable) {
		logger.info("findBookByshopid with shopid : {} and pageable : {}", shopid,pageable);
		return bookRepo.findByshopid(shopid, pageable);
	}
	@Override
	public List<Books> findByshopidv2(Integer shopid) {
		return bookRepo.findByShopid(shopid);
	}

	@Override
	public List<Books> findTop5LowestQuantityBooksByShopId(Integer shopId) {
		List<Books> booksWithSameShopId = bookRepo.findByShopid(shopId);

		booksWithSameShopId.sort((book1, book2) -> book2.getQuantitysold().compareTo(book1.getQuantitysold()));
		List<Books> top5LowestQuantityBooks = booksWithSameShopId.stream()
				.limit(5)
				.collect(Collectors.toList());
		return top5LowestQuantityBooks;
	}

	@Override
	public List<Publishingcompanies> getPCWithShop(Integer shopid) {
		// TODO Auto-generated method stub
		logger.info("getPCWithShop with shopid : {}", shopid);
		return bookRepo.getPCWithShop(shopid);
	}




	@Override
	public Page<Books> getBooksByCategoryID(Integer categories, Pageable pageable) {
		// TODO Auto-generated method stub
		logger.info("getBooksByCategoryID with shopid : {} and pageable : {}", categories,pageable);
		return bookRepo.findBooksByCategoryID(categories, pageable);
	}

//	@Override
//	public Page<Books> findBooksNew(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Page<Books> findBooksNew(Pageable pageable) {
		// TODO Auto-generated method stub
		logger.info("findBooksNew with pageable : {}",pageable);
		return bookRepo.findBooksNew(pageable);
	}
	@Override
	public void updateIsActive(Long bookId, boolean newIsActive) {
		Optional<Books> optionalBook = bookRepo.findById(bookId);
		if (optionalBook.isPresent()) {
			Books book = optionalBook.get();
			book.setIsactive(newIsActive);
			bookRepo.save(book);
		}
	}

	@Override
	public List<Integer> getBookWithTypeBook(List<Integer> listtype) {
		// TODO Auto-generated method stub
		logger.info("getBookWithTypeBook with listtype : {}",listtype);
		return bookRepo.getListBookWithTypeBooks(listtype);
	}

	@Override
	public List<Integer> getBookWithWriters(List<Integer> listwriter) {
		// TODO Auto-generated method stub
		logger.info("getBookWithWriters with listwriter : {}",listwriter);
		return bookRepo.getListBookWithWriter(listwriter);
	}

	@Override
	public List<Integer> getBookWithEvaluate(List<Integer> listeva) {
		// TODO Auto-generated method stub
		logger.info("getBookWithEvaluate with listeva : {}",listeva);
		return bookRepo.getListBookWithEvaluer(listeva);
	}

	@Override
	public List<Books> findByShopList(Integer shopid) {
		// TODO Auto-generated method stub
		logger.info("findByShopList with shopid : {}",shopid);
		return bookRepo.findByShopid(shopid);
	}
}
