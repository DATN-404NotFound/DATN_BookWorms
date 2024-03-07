package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    List<Role> findAll();

    Role findByName(String name);

    Role findSellerByRoleId(String roleId);

    Role save(Role role);
}
