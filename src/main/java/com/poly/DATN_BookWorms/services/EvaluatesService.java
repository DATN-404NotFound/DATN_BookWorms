package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.Evaluate;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EvaluatesService {

    Integer sumDbidByEvaluateId(Integer id);

    List<Evaluate> getEvaByBookid(Long bookid);
}
