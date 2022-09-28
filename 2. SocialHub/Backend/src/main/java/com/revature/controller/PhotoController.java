package com.revature.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.model.Account;
import com.revature.model.Photo;
import com.revature.repository.PhotoRepository;
import com.revature.service.AWSS3Service;
import com.revature.service.PhotoService;

@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private AWSS3Service awsS3Service;

	@Autowired
	private PhotoRepository photoRepository;

	// for main pages
	@GetMapping(path = "/all")
	public List<Photo> findAllPhotos() {
		return this.photoService.findAllPhotos();
	}

	// for group pages
	@GetMapping(path = "/{tag}")
	public List<Photo> findAllPhotosByTag(@PathVariable String tag) {
		return this.photoService.findAllPhotosByTag(tag);
	}

	@PostMapping("/upload")
	public ResponseEntity<Map<String, String>> uploadFile(@RequestParam MultipartFile file, String description,
			String tag, HttpServletRequest request) {
		String publicURL = awsS3Service.uploadFile(file);

		// for photo to DB functionality
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("accountId");

		Account account = new Account(id, "", "", "", "");
		Photo photo = new Photo(0, publicURL, description, tag, 0, 0, account);
		photoRepository.save(photo);
		// ----------------------------
		Map<String, String> response = new HashMap<>();
		response.put("publicURL", publicURL);
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
	}
}
