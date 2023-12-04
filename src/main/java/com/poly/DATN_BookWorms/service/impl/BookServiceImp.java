package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repo.ImagebooksRepo;
import com.poly.DATN_BookWorms.repo.TypebooksRepo;
import com.poly.DATN_BookWorms.response.BookResponse;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.service.BookService;

@Service
public class BookServiceImp implements BookService{

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
		return bookRepo.findAll();
	}

	@Override
	public Books findById(Long id) {
		// TODO Auto-generated method stub
		return bookRepo.findById(id).get();
	}

	@Override
	public Page<Books> findAll(Pageable pageable) {
		return bookRepo.findAll(pageable);
	}


//	@Override
//	public List<Books> findByCategoryId(String cid) {
//		return bookRepo.findByCategoryId(cid);
//	}

	@Override
	public Books creates(Books books) {
		Account user = sessionService.get("user");
			books.setBookname(books.getBookname());
			books.setLanguage(books.getLanguage());
			books.setSize(books.getSize());
			books.setWeight(books.getWeight());
			books.setTotalpage(books.getTotalpage());
			books.setPublishingyear(books.getPublishingyear());
			books.setPrice(books.getPrice());
			books.setQuantity(books.getQuantity());
			books.setStatues("Còn hàng");
			books.setPublishingcompanyid(books.getPublishingcompanyid());
			books.setIsactive(books.getIsactive());
			books.setQuantitysold(0);
			books.setShopid(user.getListOfShoponlines().get(0).getShopid());
		return bookRepo.save(books);




//		typebooksRepo.save(typebooks);
//		for (MultipartFile image : imagess) {
//			try {
//				String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
//				String uploadDir = "D:/Work/DATN_BookWorms/src/main/resources/static/Client/images";
//				FileUploadUtil.saveFile(uploadDir, fileName, image);
//				imagebooks.setBookid(books.getBookid().intValue());
//				imagebooks.setName(fileName);
//				imagebooks.setTypefile("image");
//
//				imagebooksRepo.save(imagebooks);
//			} catch (IOException e) {
//				System.out.println("not save image");
//				e.printStackTrace();
//			}
//		}
//
//		return books;



	}

	@Override
	public Books update(Books book) {
		// TODO Auto-generated method stub
		return bookRepo.save(book);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		bookRepo.deleteById(id);
	}

	@Override
	public List<Books> getBooksByCategoryID(Integer categoryID) {
		return bookRepo.findBooksByCategoryID(categoryID);
	}
	@Override
	public Page<BookResponse> findAllBook(Pageable pageable) {
		return bookRepo.findAllBook(pageable);
	}

	@Override
	public Books findTopBookByQuantitySold() {
		return bookRepo.findFirstByOrderByQuantitysoldDesc();
	}

	@Override
	public Page<Books> findByshopid(Integer shopid, Pageable pageable) {
		return bookRepo.findByshopid(shopid, pageable);
	}
	public List<Books> findByshopidv2(Integer shopid) {
		List<Books> allBooks = bookRepo.findByShopid(shopid);
		List<Books> activeBooks = allBooks.stream()
				.filter(book -> Boolean.FALSE.equals(book.getIsdelete()))
				.collect(Collectors.toList());
		return activeBooks;
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
		return bookRepo.getPCWithShop(shopid);
	}




	@Override
	public Page<Books> getBooksByCategoryID(Integer categories, Pageable pageable) {
		// TODO Auto-generated method stub
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
	public void deleteBook(Long bookId, boolean newIsActive) {
		Optional<Books> optionalBook = bookRepo.findById(bookId);
		if (optionalBook.isPresent()) {
			Books book = optionalBook.get();
			book.setIsdelete(newIsActive);
			bookRepo.save(book);
		}
	}

}
