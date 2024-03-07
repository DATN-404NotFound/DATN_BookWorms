package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.ShippingUnit;
import org.springframework.stereotype.Service;

@Service
public interface ShippingUnitService {

    List<ShippingUnit> getAll();
}
