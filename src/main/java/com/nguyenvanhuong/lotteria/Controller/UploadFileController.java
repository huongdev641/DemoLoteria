package com.nguyenvanhuong.lotteria.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenvanhuong.lotteria.Service.UploadImageFileService;

@RequestMapping("/upload")
@RestController
public class UploadFileController {
	@Autowired
	UploadImageFileService fileService;
	
	@PostMapping
	public String upLoadImage(@RequestParam("file")MultipartFile file) throws IOException {
		return fileService.upLoadImage(file);
	}
}
