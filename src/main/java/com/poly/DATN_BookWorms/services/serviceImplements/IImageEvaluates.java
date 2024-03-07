package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.entities.ImageEvaluate;
import com.poly.DATN_BookWorms.repositories.ImageevaluatesRepo;
import com.poly.DATN_BookWorms.services.ImageEvaluatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IImageEvaluates implements ImageEvaluatesService {
    @Autowired
    ImageevaluatesRepo imageevaluatesRepo;

    @Override
    public void save(ImageEvaluate imageEvaluate){
        imageevaluatesRepo.save(imageEvaluate);
    }
}
