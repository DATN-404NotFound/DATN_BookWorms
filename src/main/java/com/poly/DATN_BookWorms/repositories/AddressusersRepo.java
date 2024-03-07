package com.poly.DATN_BookWorms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.AddressUser;

import java.util.List;

public interface AddressusersRepo extends JpaRepository<AddressUser, String>{

    @Query("select ad from AddressUser ad where ad.userid = ?1")
    List<AddressUser> findByUserId(String userId);

    @Query(value = "SELECT MAX(CAST(SUBSTRING(a.addressuserid, CHARINDEX('UID', a.addressuserid) + 3, LEN(a.addressuserid) - CHARINDEX('UID', a.addressuserid) - 2) AS INT)) FROM Addressusers a WHERE CHARINDEX('UID', a.addressuserid) > 0", nativeQuery = true)
    Integer findMaxFirstPartOfId();

    @Query(value = "SELECT RIGHT(REPLICATE('0', 2) + CAST((CAST(?1 AS int) + 1) AS varchar), 2)", nativeQuery = true)
    String generateNewSecondPart(Integer maxFirstPart);
 
}
