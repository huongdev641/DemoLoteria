package com.nguyenvanhuong.lotteria.mapper;

import org.mapstruct.Mapper;

import com.nguyenvanhuong.lotteria.dto.request.UserCreateRequest;
import com.nguyenvanhuong.lotteria.dto.response.UserReponse;
import com.nguyenvanhuong.lotteria.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(UserCreateRequest request);
	UserReponse toUserReponse(User user);
}
