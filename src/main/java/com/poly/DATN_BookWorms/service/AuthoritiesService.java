package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Authorities;

@Service
public interface AuthoritiesService {
	
	public List<Authorities> findAuthoritiesOfAdministrators();

	public List<Authorities> findAll();

	public Authorities create(Authorities authorities);

	public void delete(String id);

	public List<Authorities> getOneByRole(String username);
	
}
