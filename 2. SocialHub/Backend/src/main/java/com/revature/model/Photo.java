package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    

    private String urllink;
    private String description;
    private String tag;
    private float avgRating;
    private int ratingsCount;
    
    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private Account uploadedBy;
    
    /*
    @OneToMany(mappedBy = "photo")
    private Set<Rating> ratings;
    */
    
    
}