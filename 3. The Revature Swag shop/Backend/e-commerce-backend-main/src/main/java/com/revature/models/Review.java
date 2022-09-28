package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {
	
	@Id
	@Column(name = "review_id")
	@GeneratedValue(generator = "review_review_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "review_review_id_seq")
	private int id;
	@Column(name = "review_score")
	private int score;
	@Column(name = "review_text")
	private String text;
	@Column(name = "review_user")
	private String user;
	@Column(name = "review_product")
	private int product;
}
