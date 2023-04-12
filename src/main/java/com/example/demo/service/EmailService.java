package com.example.demo.service;

import com.example.demo.entity.EmailDetails;

//Interface
public interface EmailService {

	// Method
	// To send a simple email
	 String sendSimpleMail(EmailDetails details);

	// Method
	// To send an email with attachment
	String sendMailWithAttachment(EmailDetails details);
}
