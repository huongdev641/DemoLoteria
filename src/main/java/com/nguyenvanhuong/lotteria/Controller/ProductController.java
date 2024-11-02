package com.nguyenvanhuong.lotteria.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenvanhuong.lotteria.Service.ProductService;

import com.nguyenvanhuong.lotteria.dto.response.ApiResponse;
import com.nguyenvanhuong.lotteria.dto.response.ProductReponse;
import com.nguyenvanhuong.lotteria.entity.Category;
import com.nguyenvanhuong.lotteria.entity.Product;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {
	@Autowired
	ProductService productService;
	
	@PostMapping
	public ApiResponse<ProductReponse> createResponse(@RequestParam("productName") String productName, 
		@RequestParam("price") long price,@RequestParam("categoryId") Category cId, @RequestParam("image") MultipartFile image) throws IOException{
		ApiResponse<ProductReponse> apiResponse=new ApiResponse<>();
		apiResponse.setResult(productService.createProduct(productName, price, cId, image));
		return apiResponse;
	}
	@GetMapping
	public ApiResponse<List<Product>> getProducts(){
		return ApiResponse.<List<Product>>builder().result(productService.getProducts()).build();
	}
}
