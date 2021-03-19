/**
 * 
 */
package com.adidas.dao;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {

	private boolean success;
	private String message;
	private List<String> photoUrls = new ArrayList<String>();
	private List<String> titleList = new ArrayList<String>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @param success
	 */
	public ResponseObject(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<String> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}

}
