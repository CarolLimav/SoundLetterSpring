package com.example.sound.Models;

public class ApiResponse {
	 private String status;
	    private String message;
	    private String errorType;

	    public ApiResponse(String status, String message, String errorType) {
	        this.status = status;
	        this.message = message;
	        this.errorType = errorType;
	    }

	    // Getters e Setters
	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getErrorType() {
	        return errorType;
	    }

	    public void setErrorType(String errorType) {
	        this.errorType = errorType;
	    }
}
