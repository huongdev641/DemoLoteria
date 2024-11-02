package com.nguyenvanhuong.lotteria.mapper;

import org.mapstruct.Mapper;



import com.nguyenvanhuong.lotteria.dto.response.CategoryReponse;
import com.nguyenvanhuong.lotteria.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	CategoryReponse toCategoryReponse(Category category);
}
