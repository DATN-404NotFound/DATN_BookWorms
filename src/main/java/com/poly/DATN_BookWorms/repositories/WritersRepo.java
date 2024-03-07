package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Writer;
import com.poly.DATN_BookWorms.entities.WritingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WritersRepo extends JpaRepository<Writer, Integer>{
	@Query("Select w.writingMaster from Writer w where w.bookid in (Select b.bookid from Book b where b.shopid = ?1)")
    List<WritingMaster> listWrittingByType(Integer shopid);

    List<Writer> findBybookid(Integer bookId);
}
