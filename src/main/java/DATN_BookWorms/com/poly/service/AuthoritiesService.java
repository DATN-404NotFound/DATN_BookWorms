package com.poly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.entities.Authorities;

@Service
public interface AuthoritiesService {
	
	public List<Authorities> findAuthoritiesOfAdministrators();

	public List<Authorities> findAll();

	public Authorities create(Authorities authorities);

	public void delete(String id);

	public List<Authorities> getOneByRole(String username);
	
}
