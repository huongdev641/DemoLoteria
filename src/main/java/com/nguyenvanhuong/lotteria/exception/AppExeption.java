package com.nguyenvanhuong.lotteria.exception;

public class AppExeption extends RuntimeException {
	
	public AppExeption(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	private ErrorCode errorCode;

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
}