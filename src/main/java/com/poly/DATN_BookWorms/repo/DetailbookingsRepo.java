package com.poly.DATN_BookWorms.repo;

import java.util.List;
import java.util.Map;

import com.poly.DATN_BookWorms.beans.CategoryRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import org.springframework.data.jpa.repository.Query;

public interface DetailbookingsRepo extends JpaRepository<Detailbookings, String> {
    @Query("select db from Detailbookings db where db.bookingid = ?1")
    List<Detailbookings> findByBookingId(String bookingId);
    @Query("select t.categoryid ,count(t.bookid) from Typebooks  t group by t.categoryid, t.bookid having t.bookid in (select db.bookid    from Detailbookings db group by  db.bookid having db.bookid  in (Select b.bookid from Books b where b.shopid = ?1 )) order by count(t.bookid) desc ")
    List<Object> findCateRankByShopId(Integer shopId);

}

