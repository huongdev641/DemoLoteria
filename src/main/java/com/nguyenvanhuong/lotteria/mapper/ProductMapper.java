package com.nguyenvanhuong.lotteria.mapper;

import org.mapstruct.Mapper;

import com.nguyenvanhuong.lotteria.dto.response.ProductReponse;
import com.nguyenvanhuong.lotteria.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductReponse toProductReponse(Product product);
}
