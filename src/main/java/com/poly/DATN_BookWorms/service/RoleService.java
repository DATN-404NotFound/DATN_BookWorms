package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Roles;

@Service
public interface RoleService {

	public List<Roles> findAll();
	public Roles findByName(String name);
}
