package com.nguyenvanhuong.lotteria.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenvanhuong.lotteria.entity.Product;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Integer>{
	
}
