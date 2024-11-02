package com.nguyenvanhuong.lotteria.dto.response;

import com.nguyenvanhuong.lotteria.entity.Category;

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
public class ProductReponse {
	int productId;
	String productName;
	long price;
	Category categoryId;
	String image;
}
