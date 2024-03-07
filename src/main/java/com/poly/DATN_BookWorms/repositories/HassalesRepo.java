package com.poly.DATN_BookWorms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.DATN_BookWorms.entities.HasSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HassalesRepo extends JpaRepository<HasSale, Integer>{
    @Query("SELECT h FROM HasSale h WHERE h.saleid = :couponCode")
    List<HasSale> findAllByCouponCode(@Param("couponCode") String couponCode);
    
    List<HasSale> findAllBysaleid(String saleId);

    @Query("SELECT h.hassaleid FROM HasSale h WHERE h.bookid = ?1")
    Integer findByBookId(Integer bookId);
    @Query("SELECT  h.hassaleid FROM HasSale h WHERE h.bookid = ?1 and h.saleid = ?2")
    Integer findHasSaleIdByBookId(Integer bookId, String saleId);
}
