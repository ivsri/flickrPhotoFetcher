package com.flickrPhotoSearch.service;

import org.springframework.stereotype.Service;

import com.flickrPhotoSearch.dao.ResponseObject;

@Service
public interface FlickrPhotoRetriever {

	public ResponseObject getFlickrPhotosByTags(String searchTag);

	public ResponseObject searchPhotosByTag(String searchTag);

}
