package com.nguyenvanhuong.lotteria.dto.request;

import java.util.Date;
import java.util.Set;

import jakarta.validation.constraints.Size;
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
public class UserCreateRequest {
	String name;
	Date dob;
	String email;
	String address;
	@Size(min = 10, max = 10, message = "PHONENUMBER_INVALID")
	String phoneNumber;
	@Size(min = 8, message = "PASSWORD_INVALID")
	String password;
	boolean status=true;

}
