package com.example.demo.controller;
//Java Program to Create Rest Controller that
//Defines various API for Sending Mail
//Importing required classes
import com.example.demo.entity.EmailDetails;
import com.example.demo.entity.Register;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.service.EmailService;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Annotation
@RestController
//Class
public class EmailController {
	
@Autowired
RegisterRepository registerRepository;
	

	@Autowired private EmailService emailService;

	// Sending a simple Email
	@CrossOrigin
	@PostMapping("/sendMail")
	public String
	sendMail(@RequestBody EmailDetails details)
	{
		Random rand = new Random();
		int otp = rand.nextInt(1000000);
		System.out.println("OTP = " + otp);
		Register rst = new Register();
		
		registerRepository.save(rst);
		String status = emailService.sendSimpleMail(details);

		return status;
	}

	// Sending email with attachment
	@CrossOrigin
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(
		@RequestBody EmailDetails details)
	{
		String status
			= emailService.sendMailWithAttachment(details);

		return status;
	}
		
}
