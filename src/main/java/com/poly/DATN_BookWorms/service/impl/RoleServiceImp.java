package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Roles;
import com.poly.DATN_BookWorms.repo.RoleRepo;
import com.poly.DATN_BookWorms.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{

	@Autowired
	RoleRepo roleRepo;
	
	@Override
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

	@Override
	public Roles findByName(String name){
		return new Roles();
	}
}
