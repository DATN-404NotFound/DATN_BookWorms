package com.poly.DATN_BookWorms.service;

import com.poly.DATN_BookWorms.repo.EvaluatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface EvaluatesService {

    Integer sumDbidByEvaluateId(Integer id);
}
