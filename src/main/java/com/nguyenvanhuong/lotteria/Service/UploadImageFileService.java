package com.nguyenvanhuong.lotteria.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadImageFileService {
	private final Cloudinary cloudinary;
	
	public String upLoadImage(MultipartFile file) throws IOException {
		assert file.getOriginalFilename()!=null;
		String publicValue= genaratePublicValue(file.getOriginalFilename());
		String extention = getFileName(file.getOriginalFilename())[1];
		File fileUpload=convert(file);
		cloudinary.uploader().upload(fileUpload, ObjectUtils.asMap("public_id", publicValue));
		
		cloudinary.url().generate(StringUtils.join(publicValue,".",extention));
		cleanDisk(fileUpload);
		return cloudinary.url().generate(StringUtils.join(publicValue,".",extention));
	}
	
	private File convert(MultipartFile file) throws IOException {
		assert file.getOriginalFilename() != null;
		File convFile=new File(StringUtils.join(genaratePublicValue(file.getOriginalFilename()), getFileName(file.getOriginalFilename())[1]));
		try(InputStream is=file.getInputStream()){
			Files.copy(is, convFile.toPath());
		}
		return convFile;
	}
	
	private void cleanDisk(File file) {
		try {
			Path filePath=file.toPath();
			Files.delete(filePath);
		}catch (IOException e) {
			log.error("error");
		}
	}
	
	public String genaratePublicValue(String originalName) {
		String fileName=getFileName(originalName)[0];
		return StringUtils.join(UUID.randomUUID().toString(), "_", fileName);
	}
	
	public String[] getFileName(String originalName) {
		return originalName.split("\\.");
	}
}
