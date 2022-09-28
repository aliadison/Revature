package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Account;
import com.revature.model.Photo;
import com.revature.model.Rating;



public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findAllByPhoto(Photo photo);
	
	Rating findByPhotoAndRatingBy(Photo photo, Account accountId);
	
}
