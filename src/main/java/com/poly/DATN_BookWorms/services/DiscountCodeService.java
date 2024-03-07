package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.DiscountCode;
import org.springframework.stereotype.Service;

@Service
public interface DiscountCodeService {

    List<DiscountCode> findAll();

    DiscountCode findSalesId(String salesid, String userid);

    List<DiscountCode> findDisountForSys(String userid);

    List<DiscountCode> findDisountOfShopWithUser(String intend, String userid, int shopid);

    DiscountCode findById(Integer discountid);

    DiscountCode create(DiscountCode discount);

    void delete(Integer discount);

    DiscountCode update(DiscountCode discountid);

    List<DiscountCode> findDisountByUserId(String userid);


}
