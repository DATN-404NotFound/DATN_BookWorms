package com.poly.DATN_BookWorms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.DATN_BookWorms.entities.Evaluates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvaluatesRepo extends JpaRepository<Evaluates, Integer>{
    @Query("SELECT COUNT(e.dbid) FROM Evaluates e WHERE e.evaluateid = :id")
    Integer sumDbidByEvaluateId(Integer id);
}
