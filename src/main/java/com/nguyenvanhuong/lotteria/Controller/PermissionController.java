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

import com.nguyenvanhuong.lotteria.Service.PermissionService;
import com.nguyenvanhuong.lotteria.dto.request.PermissionRequest;
import com.nguyenvanhuong.lotteria.dto.response.ApiResponse;
import com.nguyenvanhuong.lotteria.dto.response.PermissionReponse;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class PermissionController {
	@Autowired
	PermissionService permissionService;
	
	@PostMapping
	public ApiResponse<PermissionReponse> create(@RequestBody PermissionRequest request){
		ApiResponse<PermissionReponse> apiResponse=new ApiResponse<>();
		apiResponse.setResult(permissionService.create(request));
		return apiResponse;
	}
	
	@GetMapping
	public ApiResponse<List<PermissionReponse>> getAll(){
		return ApiResponse.<List<PermissionReponse>>builder().result(permissionService.getAll()).build();
	}
	
	@DeleteMapping("/{name}")
	public ApiResponse<Void> delete(@PathVariable String name){
		permissionService.delete(name);
		return ApiResponse.<Void>builder().build();
	}
}
