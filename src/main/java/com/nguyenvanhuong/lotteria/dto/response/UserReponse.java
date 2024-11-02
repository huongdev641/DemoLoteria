package com.nguyenvanhuong.lotteria.dto.response;

import java.util.Date;
import java.util.Set;

import com.nguyenvanhuong.lotteria.entity.Role;

import jakarta.persistence.ManyToMany;
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
public class UserReponse {
	String name;
	Date dob;
	String email;
	String address;
	String phoneNumber;
	boolean status;
	
	Set<Role> roles;
}
