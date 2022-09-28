package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Rating;
import com.revature.service.RatingService;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials="true")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	//for testing purposes
	@GetMapping("/{photoId}")
	public List<Rating> findAllByPhoto(@PathVariable int photoId) {
		return this.ratingService.findAllByPhoto(photoId);
	}
	
	@PostMapping("/new")
	public Rating saveNewRating(String newStarValue, String photoId, HttpServletRequest request) {
		return this.ratingService.saveRating(newStarValue,photoId,request);
	}
	//rating for individual post
	@PostMapping("/photo")
	public int findRating(String photoId, HttpServletRequest request) {
		return this.ratingService.findRating(photoId,request);
	}
	
}
