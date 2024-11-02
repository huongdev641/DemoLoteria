package com.nguyenvanhuong.lotteria.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenvanhuong.lotteria.entity.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, Integer> {
	boolean existsByCategoryName(String categoryName);
}
