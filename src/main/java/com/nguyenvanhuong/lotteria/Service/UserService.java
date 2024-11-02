package com.nguyenvanhuong.lotteria.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nguyenvanhuong.lotteria.dto.request.UserCreateRequest;
import com.nguyenvanhuong.lotteria.dto.response.UserReponse;
import com.nguyenvanhuong.lotteria.entity.Role;
import com.nguyenvanhuong.lotteria.entity.User;
import com.nguyenvanhuong.lotteria.enums.Roles;
import com.nguyenvanhuong.lotteria.exception.AppExeption;
import com.nguyenvanhuong.lotteria.exception.ErrorCode;
import com.nguyenvanhuong.lotteria.mapper.UserMapper;
import com.nguyenvanhuong.lotteria.reponsitory.RoleRepository;
import com.nguyenvanhuong.lotteria.reponsitory.UserReponsitory;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserService {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserReponsitory userReponsitory;
	@Autowired
	UserMapper userMapper;
	
	public UserReponse createUser(UserCreateRequest request) {
		if (userReponsitory.existsByPhoneNumber(request.getPhoneNumber())) {
			throw new AppExeption(ErrorCode.USER_EXISTED);
		}
		
		User user=userMapper.toUser(request);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		var role=roleRepository.findByName("USER");
		Set<Role> roles=new HashSet<>(role);
		user.setRoles(roles);
		return userMapper.toUserReponse(userReponsitory.save(user));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getUsers(){
		return userReponsitory.findAll();
	}	
	@PostAuthorize("returnObject.phoneNumber==authentication.name")
	public UserReponse getUser(Long id) {
		log.info("this is postauthoration");
		return userMapper.toUserReponse(userReponsitory.findById(id).orElseThrow(()->new RuntimeException("User not found")));
	}
	
	public UserReponse myInfo() {
		var context=SecurityContextHolder.getContext();
		String name=context.getAuthentication().getName();
		User user=userReponsitory.findByPhoneNumber(name).orElseThrow(()->new AppExeption(ErrorCode.USER_NOT_EXIST));
		return userMapper.toUserReponse(user);
	}
	
}
