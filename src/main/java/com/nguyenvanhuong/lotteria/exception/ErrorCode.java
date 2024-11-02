package com.nguyenvanhuong.lotteria.exception;

public enum ErrorCode {
	UNCATEGORIZED(9999, "Uncategorized error"),
	USER_EXISTED(1001,"Số điện thoại đã tồn tại"),
	PHONENUMBER_INVALID(1002,"số điện thoại phải có đúng 10 ký tự"),
	PASSWORD_INVALID(1003,"mật khẩu phải có ít nhất 8 ký tự"),
	USER_NOT_EXIST(1004, "user not existed"),
	UNAUTHENTICATED(1005, "unauthenticated"),
	INVALID_DOB(1006, "Invalid date of birth"),
	;
	private int code;
	private String message;
	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
