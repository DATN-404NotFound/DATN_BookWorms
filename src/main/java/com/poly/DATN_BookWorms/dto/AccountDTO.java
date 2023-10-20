package com.poly.DATN_BookWorms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String userid;
    private String username;

    private String fullname;

    private String password;

}