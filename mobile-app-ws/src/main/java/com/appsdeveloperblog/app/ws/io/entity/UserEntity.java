package com.appsdeveloperblog.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5313493413859894403L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)	
	private String userId;
	
	@Column(nullable=false)	
	private String firstName;
		
	private String lastName;
	private String email;
	private String encryptedPassword;
	private String emailVerificationToken;
	private String emailVerificationStatus;

}
