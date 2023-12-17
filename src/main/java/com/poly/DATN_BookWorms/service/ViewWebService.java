package com.poly.DATN_BookWorms.service;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.ViewWeb;


@Service
public interface ViewWebService {

    public ViewWeb save(ViewWeb viewWeb);

}
