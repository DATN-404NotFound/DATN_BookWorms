package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Role;
import com.poly.DATN_BookWorms.repositories.RoleRepo;
import com.poly.DATN_BookWorms.services.RoleService;

@Service
public class IRole implements RoleService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	RoleRepo roleRepo;
	
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

	@Override
	public Role findByName(String name){
		return new Role();
	}

	@Override
	public Role findSellerByRoleId(String roleId) {
		logger.info("find seller role by roleid :{}", roleId);
		return roleRepo.findById(roleId).get();
	}

	@Override
	public Role save(Role role) {
		logger.info("save role by roles input :{}", role.toString());
		return roleRepo.save(role);
	}
}
