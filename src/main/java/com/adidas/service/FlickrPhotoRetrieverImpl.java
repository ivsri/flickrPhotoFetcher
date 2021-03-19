package com.adidas.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adidas.dao.Photo;
import com.adidas.dao.ResponseObject;
import com.adidas.dao.Rsp;

@Service
public class FlickrPhotoRetrieverImpl implements FlickrPhotoRetriever {

	private static final Logger log = LoggerFactory.getLogger(FlickrPhotoRetrieverImpl.class);
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	PhotoRepository photoRepository;

	String flickrUrl = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg";

	@Override
	public ResponseObject getFlickrPhotosByTags(String searchTag) {
		ResponseObject responseObject = new ResponseObject(true, "Data loaded for " + searchTag);
		log.info("Request received for keyword: {}", searchTag);
		ResponseEntity<String> response = restTemplate.getForEntity(
				"https://api.flickr.com/services/rest/?api_key=6bb26f95026b741ec125679b35722233&method=flickr.photos.search&tags="
						+ searchTag,
						String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			log.info("Success response received for keyword: {}", searchTag);
			String resp = response.getBody();
			try {
				JAXBContext jaxBcontext = JAXBContext.newInstance(Rsp.class);
				Unmarshaller unmarshaller = jaxBcontext.createUnmarshaller();
				Rsp rspObj = (Rsp) unmarshaller.unmarshal(new StringReader(resp));
				if (rspObj != null && rspObj.getPhotos().getPhoto().size() > 0) {
					for (Photo photo : rspObj.getPhotos().getPhoto()) {
						photo.setSearchTag(searchTag);
						photoRepository.save(photo);
					}

				}
				log.info("Successfully stored photos in DB for tags: {}", searchTag);
			} catch (Exception e) {
				log.error("Error while searching data for keyword: {}", searchTag, e);
				responseObject.setSuccess(false);
				responseObject.setMessage("Exception:" + e.getMessage());
			}
		} else {
			responseObject.setSuccess(false);
			responseObject.setMessage("Status Code Received :" + response.getStatusCodeValue());
			log.error("Server responded with status code :{}", response.getStatusCodeValue());
		}

		return responseObject;
	}

	@Override
	public ResponseObject searchPhotosByTag(String searchTag) {
		ResponseObject responseObject = new ResponseObject(true, "");
		log.info("Search request received for keyword: {}", searchTag);
		List<Photo> listOfPhotos = photoRepository.findBySearchTagContaining(searchTag);
		if (listOfPhotos.size() > 0) {
			log.info("No. of photos found from DB: {}", listOfPhotos.size());
			List<String> responseUrls = new ArrayList<String>();
			List<String> titleList = new ArrayList<String>();
			for (Photo photo : listOfPhotos) {
				StringBuilder returnMessage = new StringBuilder();
				returnMessage.append(
						flickrUrl.replace("{farm-id}", photo.getFarm()).replace("{server-id}", photo.getServer())
						.replace("{id}", photo.getId()).replace("{secret}", photo.getSecret()));
				responseUrls.add(returnMessage.toString());
				titleList.add(photo.getTitle());
			}
			responseObject.setPhotoUrls(responseUrls);
			responseObject.setTitleList(titleList);
		} else {
			responseObject.setSuccess(false);
			responseObject.setMessage("No photos found for tags: " + searchTag);
			log.error("No photos found for tags: {}", searchTag);
		}
		return responseObject;
	}

}
