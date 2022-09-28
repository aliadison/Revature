package com.revature.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Account;
import com.revature.model.Photo;
import com.revature.model.Rating;
import com.revature.repository.PhotoRepository;
import com.revature.repository.RatingRepository;
import com.revature.util.RatingAverageCal;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private PhotoRepository photoRepository;

	private RatingAverageCal ratingAverageCal = new RatingAverageCal();

	// for testing purposes
	public List<Rating> findAllByPhoto(int photoId) {
		Photo photo = new Photo(photoId, "", "", "", 0, 0, null);
		return this.ratingRepository.findAllByPhoto(photo);
	}

	// to save new rating value
	public Rating saveRating(String newStarValueStr, String photoIdStr, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("accountId");

		int photoId = Integer.parseInt(photoIdStr);
		int newStarValue = Integer.parseInt(newStarValueStr);

		Account account = new Account(id, "", "", "", "");
		Photo photo = new Photo(photoId, "", "", "", 0, 0, null);
		Rating rating = new Rating();
		// if rating for photo by the user already exists, it just updates it. otherwise
		// it
		// makes a new ratings
		Rating existingRating = this.ratingRepository.findByPhotoAndRatingBy(photo, account);
		if (existingRating != null) {
			existingRating.setPoints(newStarValue);
			existingRating.setPhoto(photo);
			existingRating.setRatingBy(account);
			rating = this.ratingRepository.save(existingRating);
		} else {
			rating.setPoints(newStarValue);
			rating.setPhoto(photo);
			rating.setRatingBy(account);
			rating = this.ratingRepository.save(rating);
		}
		// finds average of photo ratings
		List<Rating> photoRatings = this.ratingRepository.findAllByPhoto(photo);

		int ratingsCount = photoRatings.size();
		float ratingsAverage = this.ratingAverageCal.findAvgRating(photoRatings);

		Photo actualPhoto = this.photoRepository.findById(photoId);
		actualPhoto.setAvgRating(ratingsAverage);
		actualPhoto.setRatingsCount(ratingsCount);
		this.photoRepository.save(actualPhoto);

		return rating;
	}

	// to find individual post rate value
	public int findRating(String photoIdStr, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("accountId");

		int photoId = Integer.parseInt(photoIdStr);

		Account account = new Account(id, "", "", "", "");
		Photo photo = new Photo(photoId, "", "", "", 0, 0, null);
		Rating rating = new Rating();
		// if rating for photo by the user already exists, it returns it. otherwise
		// it returns 0.
		Rating existingRating = this.ratingRepository.findByPhotoAndRatingBy(photo, account);
		if (existingRating != null) {
			return existingRating.getPoints();
		} else
			return 0;

	}
}
