package com.nguyenvanhuong.lotteria.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nguyenvanhuong.lotteria.dto.response.ApiResponse;



@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
		
		ApiResponse apiResponse=new ApiResponse<>();
		apiResponse.setCode(ErrorCode.UNCATEGORIZED.getCode());
		apiResponse.setMessage(ErrorCode.UNCATEGORIZED.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}
	
	@ExceptionHandler(value = AppExeption.class)
	ResponseEntity<ApiResponse> handlingRuntimeException(AppExeption exception){
		ErrorCode errorCode=exception.getErrorCode();
		ApiResponse apiResponse=new ApiResponse<>();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<ApiResponse> handlingRuntimeException(MethodArgumentNotValidException exception){
		String enumkey=exception.getFieldError().getDefaultMessage();
		ErrorCode errorCode=ErrorCode.valueOf(enumkey);
		ApiResponse apiResponse=new ApiResponse<>();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}
}
