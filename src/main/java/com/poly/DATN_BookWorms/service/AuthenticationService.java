package com.poly.DATN_BookWorms.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.auth.AuthenticationRequest;
import com.poly.DATN_BookWorms.auth.AuthenticationResponse;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Authorities;
import com.poly.DATN_BookWorms.entities.Roles;
import com.poly.DATN_BookWorms.repo.AccountRepo;
import com.poly.DATN_BookWorms.repo.AuthoritiesRepo;
import com.poly.DATN_BookWorms.repo.RoleRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
//		private final AccountRepo accountRepo;
//		private final AuthenticationManager authenticationManager;
//		private final RoleRepo roleRepo;
//		private final AuthoritiesRepo authoritiesRepo;
//		private final JwtService jwtService;
//		
//		public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//			Account account = accountRepo.findByUsername(authenticationRequest.getUsername()).orElseThrow();
//			List<Roles> roles = null;
//			if(account!=null) {
//			List<Authorities> authorities = (List<Authorities>) account.getAuthorities();
//				for (int i = 0; i < authorities.size(); i++) {
//					roles.add(authorities.get(i).getRoles());
//				}
//			}
//			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//			Set<Roles> set = new HashSet<>();
//			roles.stream().forEach(c ->set.add(new Roles(c.getRolename())));
//			
//			for (int i = 0; i < roles.size(); i++) {
//				account.setAuthorities(roles.get(i).getListOfAuthorities());
//			}
//			set.stream().forEach(i ->authorities.add(new SimpleGrantedAuthority(i.getRolename())));
//			var jwtToken = jwtService.generateToken(account, authorities);
//			var jwtRefreshToken = jwtService.generateRefreshToken(account, authorities);
//			return AuthenticationResponse.builder().token(jwtToken).refeshToken(jwtRefreshToken).build();
//		}
}
