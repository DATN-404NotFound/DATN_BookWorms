package com.poly.DATN_BookWorms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.DATN_BookWorms.entities.Authorities;

public interface AuthoritiesRepo extends JpaRepository<Authorities, String>{

}
