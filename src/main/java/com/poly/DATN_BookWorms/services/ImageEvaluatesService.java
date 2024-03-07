package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.ImageEvaluate;
import org.springframework.stereotype.Service;

@Service
public interface ImageEvaluatesService {
    void save(ImageEvaluate imageEvaluate);
}
