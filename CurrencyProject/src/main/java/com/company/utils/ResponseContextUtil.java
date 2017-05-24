package com.company.utils;

import java.io.Serializable;

public class ResponseContextUtil implements Serializable{

	private static final long serialVersionUID = 1L;

	public int result;
	
	public String message;
	
	public Object data;
	
	public ResponseContextUtil(){
		
	}

	public ResponseContextUtil(int result, String message, Object data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
