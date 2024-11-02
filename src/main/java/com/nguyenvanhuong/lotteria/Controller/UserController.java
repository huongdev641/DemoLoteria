package com.nguyenvanhuong.lotteria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvanhuong.lotteria.Service.UserService;
import com.nguyenvanhuong.lotteria.dto.request.UserCreateRequest;
import com.nguyenvanhuong.lotteria.dto.response.ApiResponse;
import com.nguyenvanhuong.lotteria.dto.response.UserReponse;
import com.nguyenvanhuong.lotteria.entity.User;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping
	public ApiResponse<UserReponse> createUser(@RequestBody @Valid UserCreateRequest request){
		ApiResponse<UserReponse> apiResponse=new ApiResponse<>();
		apiResponse.setResult(userService.createUser(request));;
		return apiResponse;
	}
	
	@GetMapping
	public ApiResponse<List<User>> getUsers(){
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("userName {}:", authentication.getName());
		authentication.getAuthorities().forEach(role->log.info(role.getAuthority()));
		return ApiResponse.<List<User>>builder().result(userService.getUsers()).build();
	}
	
	@GetMapping("/{userId}")
	public ApiResponse<UserReponse> getUser(@PathVariable("userId") Long id){
		ApiResponse<UserReponse> apiResponse=new ApiResponse<>();
		apiResponse.setResult(userService.getUser(id));
		return apiResponse;
	}
	
	@GetMapping("/myInfo")
	public ApiResponse<UserReponse> myInfo(){
	
		return ApiResponse.<UserReponse>builder().result(userService.myInfo()).build();
	}
}
