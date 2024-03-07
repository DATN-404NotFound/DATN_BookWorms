package com.poly.DATN_BookWorms.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.DATN_BookWorms.entities.Authority;

public interface AuthoritiesRepo extends JpaRepository<Authority, String>{

}
