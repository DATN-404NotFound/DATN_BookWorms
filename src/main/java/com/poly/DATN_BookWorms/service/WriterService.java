package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Writers;

@Service
public interface WriterService {

	public List<Writers> findAll();

}
