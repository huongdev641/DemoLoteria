package com.nguyenvanhuong.lotteria.reponsitory;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenvanhuong.lotteria.entity.Role;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

	List<Role> findByName(String name);


}
