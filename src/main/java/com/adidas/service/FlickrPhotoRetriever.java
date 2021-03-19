package com.adidas.service;

import org.springframework.stereotype.Service;

import com.adidas.dao.ResponseObject;

@Service
public interface FlickrPhotoRetriever {

	public ResponseObject getFlickrPhotosByTags(String searchTag);

	public ResponseObject searchPhotosByTag(String searchTag);

}
