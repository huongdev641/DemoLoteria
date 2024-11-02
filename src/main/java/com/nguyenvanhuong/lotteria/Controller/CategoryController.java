package com.nguyenvanhuong.lotteria.Controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenvanhuong.lotteria.Service.CategoryService;

import com.nguyenvanhuong.lotteria.dto.response.ApiResponse;
import com.nguyenvanhuong.lotteria.dto.response.CategoryReponse;
import com.nguyenvanhuong.lotteria.entity.Category;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@PostMapping
	public ApiResponse<CategoryReponse> createCategory(@RequestParam("categoryName") String categoryName,@RequestParam("image") MultipartFile image){
		ApiResponse<CategoryReponse> apiResponse=new ApiResponse<>();
		try {
			apiResponse.setResult(categoryService.createCategory(categoryName, image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apiResponse;
	}
	
	@GetMapping
	public ApiResponse<List<Category>> getCategory(){
		return ApiResponse.<List<Category>>builder().result(categoryService.getCategory()).build();
	}
}
