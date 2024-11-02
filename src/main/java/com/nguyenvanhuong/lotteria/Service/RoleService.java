package com.nguyenvanhuong.lotteria.Service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenvanhuong.lotteria.dto.request.RoleRequest;
import com.nguyenvanhuong.lotteria.dto.response.RoleReponse;
import com.nguyenvanhuong.lotteria.mapper.RoleMapper;
import com.nguyenvanhuong.lotteria.reponsitory.PermissionRepository;
import com.nguyenvanhuong.lotteria.reponsitory.RoleRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleService {
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleMapper mapper;
	
	public RoleReponse create(RoleRequest request) {
		var role=mapper.toRole(request);
		var permission=permissionRepository.findAllById(request.getPermissions());
		role.setPermissions(new HashSet<>(permission));
		return mapper.toRoleReponse(roleRepository.save(role));
	}
	
	public List<RoleReponse> getAll(){
		var role=roleRepository.findAll();
		return role.stream().map(t -> mapper.toRoleReponse(t)).toList();
	}
	
	public void delete(String name) {
		roleRepository.deleteById(name);
	}
}
