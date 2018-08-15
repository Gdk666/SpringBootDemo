package com.Guoz.utils.Exception;

public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	public AppException() {
		super();
		
	}

	public AppException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public AppException(String code, String message) {
		super(message);
		this.code = code;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AppException(String message) {
		super(message);
		
	}

	public AppException(Throwable cause) {
		super(cause);
		
	}

}
