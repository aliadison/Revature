package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Photo;
import com.revature.repository.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;
	
	public List<Photo> findAllPhotos() {
		return this.photoRepository.findAllByOrderByIdDesc();
	}
	
	public List<Photo> findAllPhotosByTag(String tag) {
		return this.photoRepository.findAllByTagOrderByIdDesc(tag);
	}
}
