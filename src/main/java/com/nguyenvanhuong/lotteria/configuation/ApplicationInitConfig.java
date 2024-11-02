package com.nguyenvanhuong.lotteria.configuation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nguyenvanhuong.lotteria.entity.Role;
import com.nguyenvanhuong.lotteria.entity.User;

import com.nguyenvanhuong.lotteria.reponsitory.RoleRepository;
import com.nguyenvanhuong.lotteria.reponsitory.UserReponsitory;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class ApplicationInitConfig {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Bean
	ApplicationRunner applicationRunner(UserReponsitory userReponsitory) {
		return args->{
			if (userReponsitory.findByPhoneNumber("admin").isEmpty()) {
				var adminRole=roleRepository.findByName("ADMIN");
				User user=User.builder().phoneNumber("admin").password(passwordEncoder.encode("admin")).build();
				Set<Role> roles = new HashSet<>(adminRole);
				user.setRoles(roles);
				userReponsitory.save(user);
			}
		};
	}
}
