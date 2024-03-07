package com.poly.DATN_BookWorms.services;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.ViewWeb;

@Service
public interface ViewWebService {
    ViewWeb save(ViewWeb viewWeb);

}
