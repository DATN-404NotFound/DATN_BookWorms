package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Writtingmasters;

@Service
public interface WriterMasterService {

	public List<Writtingmasters> findAll();

}
