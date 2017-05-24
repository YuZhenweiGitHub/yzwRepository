package com.company.utils;

import java.io.Serializable;

public class ResponseListContextUtil implements Serializable{

	private static final long serialVersionUID = 1L;

	public int result;
	
	public String message;
	
	public Object data;
	
	public int page;

	public int pagetotal;
	
	public ResponseListContextUtil(){
		
	}

	public ResponseListContextUtil(int result, String message, Object data) {
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagetotal() {
		return pagetotal;
	}

	public void setPagetotal(int pagetotal) {
		this.pagetotal = pagetotal;
	}

}
