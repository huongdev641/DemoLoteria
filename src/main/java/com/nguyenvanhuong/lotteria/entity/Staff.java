package com.nguyenvanhuong.lotteria.entity;


import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Staff extends User{
	String bankAccountNumber;
	long salary;
	String idCardNumber;
	String avatar;
	String profilePicture;
	boolean gender;
}
