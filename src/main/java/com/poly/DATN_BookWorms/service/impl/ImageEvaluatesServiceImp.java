package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Imageevaluates;
import com.poly.DATN_BookWorms.repo.ImageevaluatesRepo;
import com.poly.DATN_BookWorms.service.ImageEvaluatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageEvaluatesServiceImp implements ImageEvaluatesService {
    @Autowired
    ImageevaluatesRepo imageevaluatesRepo;

    @Override
    public void save(Imageevaluates imageevaluates){
        imageevaluatesRepo.save(imageevaluates);
    }
}
