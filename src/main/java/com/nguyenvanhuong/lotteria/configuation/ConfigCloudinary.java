package com.nguyenvanhuong.lotteria.configuation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ConfigCloudinary {
	@Bean
	public Cloudinary configKey() {
		Map config = new HashMap();
		config.put("cloud_name", "dcxtucbxt");
		config.put("api_key", "367349564544352");
		config.put("api_secret", "tL9jMm0AnWKFft12waT1jsvzQ7I");
		return new Cloudinary(config);
	}
}
