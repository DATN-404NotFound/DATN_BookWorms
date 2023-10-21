package com.poly.DATN_BookWorms.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.auth.AuthenticationRequest;
import com.poly.DATN_BookWorms.auth.AuthenticationResponse;
import com.poly.DATN_BookWorms.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
//	
//	private final AuthenticationService authenticationService;
//	
//	@PostMapping("/login")
//	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
//		return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
//	}
}
