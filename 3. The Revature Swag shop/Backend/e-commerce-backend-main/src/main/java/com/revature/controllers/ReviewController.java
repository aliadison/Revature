package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.Review;
import com.revature.services.ReviewService;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class ReviewController {

	@Autowired
	private ReviewService reviewservice;
	
	public ReviewController() {
		
	}
	
	/**
	 * 
	 * @param name
	 * @return Returns all the reviews done by the specified user as an array of jsons.
	 */
	@Authorized
	@GetMapping("/user/{username}")
    public ResponseEntity<List<Review>> getReviewByName(@PathVariable("username") String name) {
		List<Review> review = reviewservice.findByUser(name);
        return ResponseEntity.ok(review);
		
    }
	
	/**
	 * 
	 * @param id
	 * @return Returns the review specified by the review id as a json.
	 */
	@GetMapping("/id/{productid}")
    public ResponseEntity<Review> getReviewById(@PathVariable("productid") int id) {
		Review review = reviewservice.findById(id);
        return ResponseEntity.ok(review);
		
    }
	
	/**
	 * 
	 * @param id
	 * @return Returns all of the reviews for the specified product as an array of jsons.
	 */
	
	@GetMapping("/name/{product}")
    public ResponseEntity<List<Review>> getReviewByProduct(@PathVariable("product") int id) {
		List<Review> review = reviewservice.findByProduct(id);
        return ResponseEntity.ok(review);
		
    }
	
	/**
	 * Saves a new review for a particular user and product and updates the review average score for the product.
	 * Should be sent in the request body as a json object.
	 * @param review
	 */
	
    @Authorized
    @PostMapping("/new")
    public void save(@RequestBody Review review) {
        this.reviewservice.updateAvg(review);
        this.reviewservice.save(review);

    }
}
