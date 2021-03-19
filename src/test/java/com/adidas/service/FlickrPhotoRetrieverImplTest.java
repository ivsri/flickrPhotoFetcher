package com.adidas.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.adidas.dao.Photo;
import com.adidas.dao.ResponseObject;

@RunWith(MockitoJUnitRunner.class)
public class FlickrPhotoRetrieverImplTest {

	@InjectMocks
	private FlickrPhotoRetrieverImpl flickrPhotoRetriever;

	@Mock
	PhotoRepository photoRepository;

	@Mock
	RestTemplate restTemplate;

	private ResponseEntity<String> getMockResponseEntity() {
		String responseInXml = createMockStringXML();

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseInXml, HttpStatus.OK);

		return responseEntity;
	}

	private ResponseEntity<String> getMockResponseEntityFailed() {
		String responseInXml = createMockStringXML();

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseInXml, HttpStatus.NO_CONTENT);

		return responseEntity;
	}

	private String createMockStringXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<rsp stat=\"ok\">");
		sb.append("<photos page=\"1\" pages=\"1\" perpage=\"1\" total=\"1\">");
		sb.append("<photo id=\"42733392084\" owner=\"88288644@N00\" secret=\"f2bab21df9\" server=\"920\" "
				+ "farm=\"1\" title=\"Little Waterfall\" ispublic=\"1\" isfriend=\"0\" isfamily=\"0\"/>");
		sb.append("</photos>");
		sb.append("</rsp>");
		return sb.toString();
	}

	@Test
	public void shouldGetFlickrPhotosByTag() {

		Mockito.when(restTemplate.getForEntity(Matchers.contains("flickr"), Matchers.eq(String.class)))
				.thenReturn(getMockResponseEntity());
		String searchTag = "Nature";
		ResponseObject responseReceived = flickrPhotoRetriever.getFlickrPhotosByTags(searchTag);
		Assert.assertTrue(responseReceived.isSuccess());
	}

	@Test
	public void shouldNotGetFlickrPhotosByTag() {
		Mockito.when(restTemplate.getForEntity(Matchers.contains("flickr"), Matchers.eq(String.class)))
				.thenReturn(getMockResponseEntityFailed());
		String searchTag = "Nature";
		ResponseObject responseReceived = flickrPhotoRetriever.getFlickrPhotosByTags(searchTag);
		Assert.assertFalse(responseReceived.isSuccess());
	}

	@Test
	public void shouldSearchPhotosByTag() {
		List<Photo> mockDbResponse = createMockDbResponse();
		Mockito.when(photoRepository.findBySearchTagContaining(Mockito.anyString())).thenReturn(mockDbResponse);
		ResponseObject responseFromSearch = flickrPhotoRetriever.searchPhotosByTag("nature");
		Assert.assertTrue(responseFromSearch.isSuccess());
	}

	@Test
	public void shouldNotSearchPhotosByTag() {
		Mockito.when(photoRepository.findBySearchTagContaining(Mockito.anyString())).thenReturn(new ArrayList<Photo>());
		String searchTag = "Nature";
		ResponseObject responseFromSearch = flickrPhotoRetriever.searchPhotosByTag(searchTag);

		Assert.assertFalse(responseFromSearch.isSuccess());
	}

	private List<Photo> createMockDbResponse() {
		List<Photo> mockDbResponse = new ArrayList<Photo>();
		Photo photo = new Photo();
		photo.setId("1");
		photo.setFarm("4");
		photo.setIsfamily("1");
		photo.setIsfriend("0");
		photo.setIspublic("1");
		photo.setOwner("owner");
		photo.setSecret("0918235fs");
		photo.setServer("433");
		photo.setTitle("title");
		mockDbResponse.add(photo);
		return mockDbResponse;
	}

}
