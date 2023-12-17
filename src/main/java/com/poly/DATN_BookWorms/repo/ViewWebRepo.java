package com.poly.DATN_BookWorms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.DATN_BookWorms.entities.ViewWeb;

@Repository
public interface ViewWebRepo extends JpaRepository<ViewWeb, Integer>{

}
