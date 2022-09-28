package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Product;
import com.revature.models.Review;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.ReviewRepository;

/**
 * Provides the service layer between review objects and review controllers.
 * @author jonat
 *
 */
@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewrepository;
	@Autowired 
	private ProductRepository productrepository;
	
	public ReviewService() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @return Returns a review object of with the specified id.
	 */
	public Review findById(int id) {
		return this.reviewrepository.findById(id);
	}
	
	/**
	 * 
	 * @param name
	 * @return Returns the full list of reviews done by a specified user name.
	 */
	public List<Review> findByUser(String name){
		return this.reviewrepository.findAllByUser(name);
	}
	
	/**
	 * 
	 * @param id
	 * @return Returns the full list of reviews for a product with the id passed in.
	 */
	public List<Review> findByProduct(int id){
		return this.reviewrepository.findAllByProduct(id);
	}
	
	/**
	 * Saves the review object passed to it. If the id is already used it will update the information. If the id hasn't been used yet
	 * or one isn't declared it saves a new review.
	 * @param review
	 */
	public void save(Review review) {
		this.reviewrepository.save(review);
	}
	
	/**
	 * Deletes a review.
	 * @param review
	 */
	public void delete(Review review) {
		this.reviewrepository.delete(review);
	}
	
	/**
	 * An internal method that updates the review score for a product after a new review has been either saved or updated.
	 * @param review
	 */
	public void updateAvg(Review review) {
		Product product = productrepository.getById(review.getProduct());
		double reviewavg = product.getReviewavg();
		int numreviews = reviewrepository.countByProduct(product.getId());
		reviewavg = (review.getScore()+reviewavg*numreviews)/(numreviews+1);
		product.setReviewavg(reviewavg);
		productrepository.save(product);
	}
}
