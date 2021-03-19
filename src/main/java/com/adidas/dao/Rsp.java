package com.adidas.dao;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rsp {

	private String stat;
	private Photos photos;

	public String getStat() {
		return stat;
	}

	@XmlAttribute
	public void setStat(String stat) {
		this.stat = stat;
	}

	public Photos getPhotos() {
		return photos;
	}

	@XmlElement
	public void setPhotos(Photos photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Rsp [stat=" + stat + ", photos=" + photos + "]";
	}

}
