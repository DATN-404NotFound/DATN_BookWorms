package com.poly.DATN_BookWorms.services;

import java.util.List;


import com.poly.DATN_BookWorms.entities.Authority;
import org.springframework.stereotype.Service;

@Service
public interface AuthoritiesService {

    List<Authority> findAuthoritiesOfAdministrators();

    List<Authority> findAll();

    Authority create(Authority authority);

    void delete(String id);

    List<Authority> getOneByRole(String username);

    void save(Authority authority);
}
