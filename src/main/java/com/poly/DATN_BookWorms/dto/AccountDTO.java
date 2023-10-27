package com.poly.DATN_BookWorms.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String userid;
    
    @NotEmpty(message = "Please enter valid username")
    private String username;
    
    @NotEmpty(message = "Please enter valid fullname")
    private String fullname;
      
    @NotEmpty(message = "Please enter valid password")
    private String password;
    
    @Email
    private String email;
    
    @NotEmpty(message = "Please enter valid password confirm")
    private String password2;

}