package com.adidas.dao;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Photos {

	private String total;

	private String page;

	private String pages;

	private String perpage;

	private List<Photo> photo;

	public String getTotal() {
		return total;
	}

	@XmlAttribute
	public void setTotal(String total) {
		this.total = total;
	}

	public String getPage() {
		return page;
	}

	@XmlAttribute
	public void setPage(String page) {
		this.page = page;
	}

	public String getPages() {
		return pages;
	}

	@XmlAttribute
	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getPerpage() {
		return perpage;
	}

	@XmlAttribute
	public void setPerpage(String perpage) {
		this.perpage = perpage;
	}

	public List<Photo> getPhoto() {
		return photo;
	}

	@XmlElement
	public void setPhoto(List<Photo> photo) {
		this.photo = photo;
	}

}
