package com.poly.DATN_BookWorms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Evaluate;

@Service
public interface EvaluateService {

    Evaluate create(Evaluate evaluate);

    List<Evaluate> findEvaluatesByShopId(Integer shopId);

    List<Evaluate> findAll();

}
