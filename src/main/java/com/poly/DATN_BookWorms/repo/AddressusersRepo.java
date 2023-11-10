package com.poly.DATN_BookWorms.repo;

import com.poly.DATN_BookWorms.entities.Detailbookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Addressusers;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressusersRepo extends JpaRepository<Addressusers, String>{

    @Query("select ad from Addressusers ad where ad.userid = ?1")
    List<Addressusers> findByUserId(String userId);
    
}
