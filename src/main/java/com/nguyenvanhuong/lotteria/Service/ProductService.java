package com.nguyenvanhuong.lotteria.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenvanhuong.lotteria.dto.response.ProductReponse;
import com.nguyenvanhuong.lotteria.entity.Category;
import com.nguyenvanhuong.lotteria.entity.Product;
import com.nguyenvanhuong.lotteria.mapper.ProductMapper;
import com.nguyenvanhuong.lotteria.reponsitory.ProductReponsitory;


@Service

public class ProductService {
	@Autowired
	ProductReponsitory productReponsitory;
	@Autowired
	UploadImageFileService fileService;
	@Autowired
	ProductMapper mapper;
	
	public ProductReponse createProduct(String pName, long price, Category cId, MultipartFile image) throws IOException{
		Product product=new Product();
		product.setProductName(pName);
		product.setPrice(price);
		product.setCategory(cId);
		product.setImage(fileService.upLoadImage(image));
		return mapper.toProductReponse(productReponsitory.save(product));
	}
	
	public List<Product> getProducts(){
		List<Product> list=productReponsitory.findAll();
		return list;
	}
}
