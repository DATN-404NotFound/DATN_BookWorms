package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.DATN_BookWorms.entities.DetailBooking;
import org.springframework.data.jpa.repository.Query;

public interface DetailbookingsRepo extends JpaRepository<DetailBooking, String> {
    @Query("select db from DetailBooking db where db.bookingid = ?1")
    List<DetailBooking> findByBookingId(String bookingId);
    @Query("select t.categoryid ,count(t.bookid) from TypeBook  t group by t.categoryid, t.bookid having t.bookid in (select db.bookid    from DetailBooking db group by  db.bookid having db.bookid  in (Select b.bookid from Book b where b.shopid = ?1 )) order by count(t.bookid) desc ")
    List<Object> findCateRankByShopId(Integer shopId);

}

