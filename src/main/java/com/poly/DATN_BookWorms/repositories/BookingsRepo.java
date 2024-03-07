package com.poly.DATN_BookWorms.repositories;


import com.poly.DATN_BookWorms.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


import org.springframework.data.repository.query.Param;


public interface BookingsRepo extends JpaRepository<Booking, String> {
    @Query("SELECT o FROM Booking o WHERE o.createat >= :startDate AND o.createat <= :endDate AND o.orderStatus.orderstatusid = 5")
    List<Booking> getIsSuccess(Date startDate, Date endDate);

    @Query("SELECT o FROM Booking o WHERE o.createat >= :startDate AND o.createat <= :endDate AND o.orderStatus.orderstatusid != 6 AND o.orderStatus.orderstatusid !=7")
    List<Booking> getIsPaid(Date startDate, Date endDate);

    @Query("Select b from Booking b where b.account.userid like ?1")
    List<Booking> findBookingByUser(String userid);

    @Query("Select b.shopOnline from Book b where b.bookid in (Select c.book.bookid from Cart c where c.account.userid like ?1 )")
    List<ShopOnline> list_cart_shopId(String userid);

    @Query("Select b from Booking b where b.orderstatusid = ?1")
    List<Booking> ListBookings_Status(String orderStatusId);


    List<Booking> findByuserid(String userId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.orderStatus.orderstatusid = 1")
    long countUnpaid();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.orderStatus.orderstatusid = 2")
    long countPaid();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.orderStatus.orderstatusid = 3")
    long countConfirm();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.orderStatus.orderstatusid = 4")
    long countDelivering();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.orderStatus.orderstatusid = 5")
    long countProcessed();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.orderStatus.orderstatusid = 6")
    long countCancel();

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.orderStatus.orderstatusid = 7")
    long countRefund();

    @Query("SELECT b FROM Booking b WHERE b.orderStatus.orderstatusid = 1")
    List<Booking> unpaid();

    @Query("SELECT b FROM Booking b WHERE b.orderStatus.orderstatusid = 2")
    List<Booking> paid();

    @Query("SELECT b FROM Booking b WHERE b.orderStatus.orderstatusid = 3")
    List<Booking> confirm();

    @Query("SELECT b FROM Booking b WHERE b.orderStatus.orderstatusid = 4")
    List<Booking> delivering();

    @Query("SELECT b FROM Booking b WHERE b.orderStatus.orderstatusid = 5")
    List<Booking> processed();

    @Query("SELECT b FROM Booking b WHERE b.orderStatus.orderstatusid = 6")
    List<Booking> cancel();

    @Query("SELECT b FROM Booking b WHERE b.orderStatus.orderstatusid = 7")
    List<Booking> refund();

    @Query("SELECT b FROM Booking b " +
            "INNER JOIN DetailBooking db ON b.bookingid = db.bookingid " +
            "INNER JOIN Book bk ON db.bookid = bk.bookid " +
            "WHERE bk.shopid = :shopid AND b.orderstatusid = :orderstatusid")
    List<Booking> findBookingsByShopIdAndOrderStatusId(@Param("shopid") Integer shopId, @Param("orderstatusid") Integer orderStatusId);

    @Query("SELECT b FROM Booking b " +
            "INNER JOIN DetailBooking db ON b.bookingid = db.bookingid " +
            "INNER JOIN Book bk ON db.bookid = bk.bookid " +
            "WHERE bk.shopid = :shopid")
    List<Booking> findBookingsByShopId(@Param("shopid") Integer shopId);

    Booking findBybookingid(String bookingId);

    @Query("SELECT b FROM Booking b WHERE b.account.userid = ?1 and b.orderStatus.orderstatusid = ?2")
    List<Booking> findByUserIdAndOrderStaturs(String userId, Integer orderstatus);
}
