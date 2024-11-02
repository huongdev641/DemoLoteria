package com.nguyenvanhuong.lotteria.Service;

import java.util.List;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenvanhuong.lotteria.dto.request.PermissionRequest;
import com.nguyenvanhuong.lotteria.dto.response.PermissionReponse;
import com.nguyenvanhuong.lotteria.entity.Permission;
import com.nguyenvanhuong.lotteria.mapper.PermissionMapper;
import com.nguyenvanhuong.lotteria.reponsitory.PermissionRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class PermissionService {
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	PermissionMapper mapper;
	
	public PermissionReponse create(PermissionRequest request) {
		Permission permission=mapper.toPermission(request);
		return mapper.toPermissionReponse(permissionRepository.save(permission));
	}
	
	public List<PermissionReponse> getAll(){
		var permission=permissionRepository.findAll();
		return permission.stream().map(t -> mapper.toPermissionReponse(t)).toList();
	}
	
	public void delete(String name) {
		permissionRepository.deleteById(name);
	}
}
