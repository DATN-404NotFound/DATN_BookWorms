package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entities.Roles;
import com.poly.repo.RoleRepo;
import com.poly.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{

	@Autowired
	RoleRepo roleRepo;
	
	@Override
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}
}
