package com.poly.DATN_BookWorms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Authorities;
import com.poly.DATN_BookWorms.entities.Roles;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	AccountService accountService;

	@Autowired
	RoleService roleService;

	@Autowired
	AuthoritiesService authoritiesService;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account = accountService.findByUsename(username);

		List<Authorities> authorities = account.getAuthorities();
		List<Roles> roles = new ArrayList<>();
		for (Authorities author : authorities) {
			roles.add(author.getRoles());
		}

		if (account != null) {
			return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
					roles.stream().map((role) -> new SimpleGrantedAuthority(role.getRoleid()))
							.collect(Collectors.toList()));
		} else
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
	}


