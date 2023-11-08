package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.repo.EvaluatesRepo;
import com.poly.DATN_BookWorms.service.EvaluatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatesServiceImp implements EvaluatesService {
    @Autowired
    EvaluatesRepo evaluatesRepo;
    @Override
    public Integer sumDbidByEvaluateId(Integer id) {
        return evaluatesRepo.sumDbidByEvaluateId(id);
    }
}
