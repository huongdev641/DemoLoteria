package com.nguyenvanhuong.lotteria.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvanhuong.lotteria.entity.InvalidatedToken;

public interface InvalidatedTokenReponsitory extends JpaRepository<InvalidatedToken,String>{

}
