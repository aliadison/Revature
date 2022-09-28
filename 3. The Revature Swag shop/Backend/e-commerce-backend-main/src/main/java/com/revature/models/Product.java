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
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(generator = "product_product_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "product_product_id_seq")
    private int id;
	@Column(name = "product_quantity")
    private int quantity;
	@Column(name = "product_price")
    private double price;
	@Column(name = "product_reviewavg")
    private double reviewavg;
	@Column(name = "product_description")
    private String description;
	@Column(name = "product_image")
    private String image;
	@Column(name = "product_name")
    private String name;
}
