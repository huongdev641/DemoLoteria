package com.nguyenvanhuong.lotteria.reponsitory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenvanhuong.lotteria.entity.User;
import java.util.Optional;


@Repository
public interface UserReponsitory extends JpaRepository<User, Long>{
	boolean existsByPhoneNumber(String phoneNumber);
	Optional<User> findByPhoneNumber(String phoneNumber);
}
