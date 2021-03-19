package com.flickrPhotoSearch.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flickrPhotoSearch.dao.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String>{


	List<Photo> findBySearchTagContaining(String searchTags);


}
