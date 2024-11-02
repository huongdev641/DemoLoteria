package com.nguyenvanhuong.lotteria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvanhuong.lotteria.dto.request.RoleRequest;
import com.nguyenvanhuong.lotteria.dto.response.RoleReponse;
import com.nguyenvanhuong.lotteria.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	@Mapping(target = "permissions", ignore = true)
	Role toRole(RoleRequest request);
	
	RoleReponse toRoleReponse(Role role);
}
