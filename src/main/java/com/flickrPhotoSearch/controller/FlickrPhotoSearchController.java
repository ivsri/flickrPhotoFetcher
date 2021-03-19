package com.flickrPhotoSearch.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flickrPhotoSearch.dao.ResponseObject;
import com.flickrPhotoSearch.service.FlickrPhotoRetriever;

@RestController
public class FlickrPhotoSearchController {

	@Autowired
	private FlickrPhotoRetriever flickrPhotoRetriever;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RequestMapping("/loadPhotosByTag")
	public ResponseObject loadPhotosByTags(@QueryParam("searchTag") String searchTag) {

		ResponseObject responseObject = flickrPhotoRetriever.getFlickrPhotosByTags(searchTag);
		return responseObject;
	}

	@RequestMapping("/searchPhotosByTag")
	public ResponseObject searchPhotosByTag(@QueryParam("searchTag") String searchTag) {

		ResponseObject responseObject = flickrPhotoRetriever.searchPhotosByTag(searchTag);
		return responseObject;
	}

}
