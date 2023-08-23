package com.upload.file.upload.message;

public class ResponseMessage {
	
	private String message;

// constructor using field
	public ResponseMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
