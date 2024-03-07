package com.poly.DATN_BookWorms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.dto.AccountDto;
import com.poly.DATN_BookWorms.entities.Account;


@Service
public interface AccountService {

    Account findByUserId(String userId);

    Account findByUsename(String username);

    Account findByEmail(String email);

    List<Account> getAdministrators();

    List<Account> findAll();

    Account create(Account account);

    Account update(Account account);

    Account changePassword(String password, String username);

    void delete(String username);

    void save(AccountDto accountDTO);

    void save(Account user);
}
