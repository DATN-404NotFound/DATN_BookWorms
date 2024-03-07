package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.ImageBook;
import org.springframework.stereotype.Service;

@Service
public interface ImagesBookService {
	public List<ImageBook> findByBookId(Long bookid);

    void save(ImageBook imageBook);

    void deleteImagebooksByBookId(Long bookId);


}
