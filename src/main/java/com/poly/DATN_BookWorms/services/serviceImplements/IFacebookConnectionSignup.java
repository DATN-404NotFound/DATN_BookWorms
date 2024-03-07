package com.poly.DATN_BookWorms.services.serviceImplements;


import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Authority;
import com.poly.DATN_BookWorms.entities.Role;

import com.poly.DATN_BookWorms.repositories.AuthoritiesRepo;
import com.poly.DATN_BookWorms.repositories.RoleRepo;
import com.poly.DATN_BookWorms.utils.CRC32Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class IFacebookConnectionSignup implements ConnectionSignUp {
    private PasswordEncoder passwordEncoder;

    @Autowired
    CRC32Utils crc32Utils;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    AuthoritiesRepo authoritiesRepo;

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup ===============");
        Role role = roleRepo.findById("FACEBOOK_GUEST").get();

        if (role == null) {
            role = roleRepo.save(new Role("FACEBOOK_GUEST","Facebook Guest", null));
        }

        long userId = crc32Utils.getCRC32Hash(connection.getDisplayName());
        Account account = new Account();
        account.setUserid(Long.toString(userId));
        account.setEmail(null);
        account.setFullname(null);
        account.setPassword(passwordEncoder.encode(RandomStringUtils.randomAlphabetic(8)));
        account.setUsername(connection.getDisplayName());
        //Create authority for new account
        long authorityId =  crc32Utils.getCRC32Hash(connection.getDisplayName()+role.getRoleid());
        authoritiesRepo.save(new Authority(Long.toString(authorityId),account, role));

        return account.getUsername();
    }
}
