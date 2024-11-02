package com.nguyenvanhuong.lotteria.dto.response;


import java.util.Set;

import com.nguyenvanhuong.lotteria.entity.Permission;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleReponse {
	String name;
	String description;
	Set<Permission> permissions;
}
