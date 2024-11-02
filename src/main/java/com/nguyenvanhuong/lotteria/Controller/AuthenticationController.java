package com.nguyenvanhuong.lotteria.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvanhuong.lotteria.Service.AuthenticationService;
import com.nguyenvanhuong.lotteria.dto.request.AuthenticationRequest;
import com.nguyenvanhuong.lotteria.dto.request.IntrospectRequest;
import com.nguyenvanhuong.lotteria.dto.request.LogoutRequest;
import com.nguyenvanhuong.lotteria.dto.response.ApiResponse;
import com.nguyenvanhuong.lotteria.dto.response.AuthenticationResponse;
import com.nguyenvanhuong.lotteria.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthenticationController {
	@Autowired
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) { 
        var result = authenticationService.authenticate(request);
        log.error("aaa");
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();   
    }
    
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws JOSEException, ParseException { 
        var result = authenticationService.introspect(request);
        log.error("aaa");
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();   
    }
    
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws JOSEException, ParseException { 
       authenticationService.logout(request);
        log.error("aaa");
        return ApiResponse.<Void>builder()
                .build();   
    }
}
