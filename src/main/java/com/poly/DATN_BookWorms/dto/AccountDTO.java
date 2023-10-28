package com.poly.DATN_BookWorms.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String userid;
    
    @NotNull(message = "Please enter valid username")
    private String username;

    @NotNull(message = "Please enter valid fullname")
    private String fullname;

    @NotNull(message = "Please enter valid password")
    private String password;
    
    @Email
    private String email;

    @NotNull(message = "Please enter valid password confirm")
    private String password2;

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public String getFullname() {
		// TODO Auto-generated method stub
		return "hjdskjfhijkds";
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return "hanguyen@gmail.com";
	}

}