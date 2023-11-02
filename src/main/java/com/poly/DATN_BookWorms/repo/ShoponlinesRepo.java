package com.poly.DATN_BookWorms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.DATN_BookWorms.entities.Shoponlines;

import java.util.Optional;

public interface ShoponlinesRepo extends JpaRepository<Shoponlines, Integer>{

}
