package com.nguyenvanhuong.lotteria.mapper;

import org.mapstruct.Mapper;

import com.nguyenvanhuong.lotteria.dto.request.PermissionRequest;
import com.nguyenvanhuong.lotteria.dto.response.PermissionReponse;
import com.nguyenvanhuong.lotteria.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
	Permission toPermission(PermissionRequest request);
	PermissionReponse toPermissionReponse(Permission permission);
	
}
