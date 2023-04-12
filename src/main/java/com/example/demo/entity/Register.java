package com.example.demo.entity;
//demo package
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name="register")
public class Register {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="details_id")
	private int details_id;
	
	@Column(name="first_name")
	private String firstName;
	
@Column(name="last_name")
private String lastName;

@Column(name="phone_number")
private long phoneNumber;

@Column(name="OTP")
private int OTP;

@Column(name="email")
private String email;


@Column(name="recipient")
	private String recipient;
@Column(name="msg_body")
	private String msgBody;
@Column(name="subjects")
	private String subject;
@Column(name="image")
	private String image;
//	private String attachment;








}