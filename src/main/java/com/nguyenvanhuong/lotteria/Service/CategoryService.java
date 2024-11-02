package com.nguyenvanhuong.lotteria.Service;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.nguyenvanhuong.lotteria.dto.response.CategoryReponse;
import com.nguyenvanhuong.lotteria.entity.Category;
import com.nguyenvanhuong.lotteria.exception.AppExeption;
import com.nguyenvanhuong.lotteria.exception.ErrorCode;
import com.nguyenvanhuong.lotteria.mapper.CategoryMapper;
import com.nguyenvanhuong.lotteria.reponsitory.CategoryReponsitory;

@Service

public class CategoryService {
	@Autowired
	CategoryReponsitory categoryReponsitory;
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	UploadImageFileService fileService;
	public CategoryReponse createCategory(String categoryName, MultipartFile image) throws IOException{
		if (categoryReponsitory.existsByCategoryName(categoryName)) {
			throw new AppExeption(ErrorCode.UNCATEGORIZED);
		}
		Category category=new Category();
		category.setCategoryName(categoryName);
		category.setImage(fileService.upLoadImage(image).toString());

		return categoryMapper.toCategoryReponse(categoryReponsitory.save(category));
		
	}
	public List<Category> getCategory() {
		return (categoryReponsitory.findAll());
	}
}
