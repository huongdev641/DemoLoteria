package com.nguyenvanhuong.lotteria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvanhuong.lotteria.Service.RoleService;
import com.nguyenvanhuong.lotteria.dto.request.RoleRequest;
import com.nguyenvanhuong.lotteria.dto.response.ApiResponse;
import com.nguyenvanhuong.lotteria.dto.response.RoleReponse;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {
	@Autowired
	RoleService roleService;
	
	@PostMapping
	public ApiResponse<RoleReponse> create(@RequestBody RoleRequest request){
		return ApiResponse.<RoleReponse>builder().result(roleService.create(request)).build();
	}
	
	@GetMapping
	public ApiResponse<List<RoleReponse>> getAll(){
		return ApiResponse.<List<RoleReponse>>builder().result(roleService.getAll()).build();
	}
	
	@DeleteMapping("/{name}")
	public ApiResponse<Void> delete(@PathVariable String name){
		roleService.delete(name);
		return ApiResponse.<Void>builder().build();
	}
	
}
