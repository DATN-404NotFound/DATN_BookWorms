package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.File;

public interface FilesRepo extends JpaRepository<File, Integer>{

	@Query("Select f from File f where f.shopid like ?1")
	public List<File> getFilesByShopID(Integer shopid);
}
