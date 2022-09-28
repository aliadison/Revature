package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(generator = "users_user_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "users_user_id_seq")
    private int id;
	@Column(name = "user_email")
    private String email;
	@Column(name = "user_password")
    private String password;
	@Column(name = "user_firstname")
    private String firstName;
	@Column(name = "user_lastname")
    private String lastName;
}
