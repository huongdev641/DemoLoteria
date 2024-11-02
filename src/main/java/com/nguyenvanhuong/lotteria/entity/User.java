package com.nguyenvanhuong.lotteria.entity;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.nguyenvanhuong.lotteria.enums.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userId;
	String name;
	Date dob;
	String email;
	String address;
	String phoneNumber;

	String password;
	boolean status;
	
	@ManyToMany
	Set<Role> roles;

}
