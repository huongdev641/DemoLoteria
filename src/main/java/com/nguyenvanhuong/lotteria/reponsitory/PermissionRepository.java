package com.nguyenvanhuong.lotteria.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenvanhuong.lotteria.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String>{

}