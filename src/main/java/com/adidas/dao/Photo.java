package com.adidas.dao;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "photo")
@EntityListeners(AuditingEntityListener.class)
public class Photo {

	@Id
	private String id;

	private String isfamily;

	private String title;

	private String ispublic;

	private String owner;

	private String secret;

	private String server;

	private String isfriend;

	private String farm;

	private String searchTag;

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getIsfamily() {
		return isfamily;
	}

	@XmlAttribute
	public void setIsfamily(String isfamily) {
		this.isfamily = isfamily;
	}

	public String getTitle() {
		return title;
	}

	@XmlAttribute
	public void setTitle(String title) {
		this.title = title;
	}

	public String getIspublic() {
		return ispublic;
	}

	@XmlAttribute
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}

	public String getOwner() {
		return owner;
	}

	@XmlAttribute
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSecret() {
		return secret;
	}

	@XmlAttribute
	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getServer() {
		return server;
	}

	@XmlAttribute
	public void setServer(String server) {
		this.server = server;
	}

	public String getIsfriend() {
		return isfriend;
	}

	@XmlAttribute
	public void setIsfriend(String isfriend) {
		this.isfriend = isfriend;
	}

	public String getFarm() {
		return farm;
	}

	@XmlAttribute
	public void setFarm(String farm) {
		this.farm = farm;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", isfamily=" + isfamily + ", title=" + title + ", ispublic=" + ispublic + ", owner="
				+ owner + ", secret=" + secret + ", server=" + server + ", isfriend=" + isfriend + ", farm=" + farm
				+ "]";
	}

	public String getSearchTag() {
		return searchTag;
	}

	public void setSearchTag(String searchTag) {
		this.searchTag = searchTag;
	}

}
