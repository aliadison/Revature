package com.revature.util;

import java.util.List;

import com.revature.model.Rating;

public class RatingAverageCal {

	public float findAvgRating(List<Rating> ratings) {
		float sum = 0;
		for (Rating rating : ratings) {
			sum += rating.getPoints();
		}
		float average = (sum / ratings.size());
		return average;
	}
}
