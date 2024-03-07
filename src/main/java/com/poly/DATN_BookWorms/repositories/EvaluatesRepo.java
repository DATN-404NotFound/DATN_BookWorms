package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.DATN_BookWorms.entities.Evaluate;
import org.springframework.data.jpa.repository.Query;

public interface EvaluatesRepo extends JpaRepository<Evaluate, Integer>{
    @Query("SELECT COUNT(e.dbid) FROM Evaluate e WHERE e.evaluateid = :id")
    Integer sumDbidByEvaluateId(Integer id);
    @Query("Select e from Evaluate e where e.dbid in (Select d.dbid from DetailBooking d where d.book.bookid = ?1)")
    List<Evaluate> getEvaByBookid(Long bookid);
    
    
    
}
