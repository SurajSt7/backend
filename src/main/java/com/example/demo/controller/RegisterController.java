package com.example.demo.controller;
//Demo Package

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmailDetails;
import com.example.demo.entity.Register;
import com.example.demo.entity.Student;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.repository.StudentRepository;

//import entity.Register;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Defaults;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class RegisterController {
	
	@Autowired
	RegisterRepository registerRepository;
	
	@Data
	@NoArgsConstructor
	public static class Data1{
	private int details_id;
	private String firstName;
	private	String lastName;
	private int phoneNumber;
	private String email;
	}


@CrossOrigin
@PostMapping("/addDetails")
public ResponseEntity<Object> addDetails(@RequestBody Data1 data){
	
	Register rst = new Register();
	
	rst.setDetails_id(data.getDetails_id());
	rst.setFirstName(data.getFirstName());
	rst.setLastName(data.getLastName());
	rst.setPhoneNumber(data.getPhoneNumber());
	System.out.println("id: "+data.getDetails_id());
	System.out.println("FN: "+data.getFirstName());
	System.out.println("LN: "+data.getLastName());
	System.out.println("Phone: "+data.getPhoneNumber());
	
	registerRepository.save(rst);
	return new ResponseEntity<Object>(HttpStatus.OK);

}

@CrossOrigin
@GetMapping("/getInfo")
public ResponseEntity<Object>getStudent(){
	
	List ls = registerRepository.findAll();
	registerRepository.getById(4);
	return new ResponseEntity<Object>(ls,HttpStatus.OK);
}
@CrossOrigin
@PostMapping("/getDetails")
public ResponseEntity<Object> getDetails(@RequestBody Data1 data){
	Register rst = new Register();
	rst.setDetails_id(data.getDetails_id());
	rst.setFirstName(data.getFirstName());
	rst.setLastName(data.getLastName());
	rst.setEmail(data.getEmail());
	
	Object a = registerRepository.findById(1);
	registerRepository.getById(1);
	return new ResponseEntity<Object>(a,HttpStatus.OK);
}

@CrossOrigin
@PostMapping("/sendOtp")
public ResponseEntity<Object>  sendOtp(@RequestBody Register rst){
	Random rand= new Random();
	int otp;
	otp = rand.nextInt(1000000);
		   System.out.println("OTP:"+otp);
		   rst.setOTP(otp);


	   SimpleMailMessage otp1
		= new SimpleMailMessage();
	
	// Setting up necessary details
	otp1.setFrom("surajst734@gmail.com");
	otp1.setTo(rst.getRecipient());
	otp1.setText("Your One Time Password is "+otp);
//	rst.setOTP(otp);
	rst.setOTP(otp);
	
//	otp.setSubject(details.getSubject());
	

	// Sending the mail
	javaMailSender.send(otp1);
	   registerRepository.save(rst);
       return new ResponseEntity<Object>(rst,HttpStatus.OK);
}


@Autowired private JavaMailSender javaMailSender;

public String sendSimpleMail(@RequestBody Register rst)
{

	// Try block to check for exceptions
	try {

		// Creating a simple mail message
		SimpleMailMessage otp
			= new SimpleMailMessage();
		
		// Setting up necessary details
		otp.setFrom("surajst734@gmail.com");
		otp.setTo(rst.getRecipient());
		otp.setText(rst.getMsgBody());
//		otp.setSubject(details.getSubject());
		

		// Sending the mail
		javaMailSender.send(otp);
		return "Mail Sent Successfully...";
	}

	// Catch block to handle the exceptions
	catch (Exception e) {
		return "Error while Sending Mail";
	}
}

public String sendMailWithAttachment(EmailDetails details)
{
	// Creating a mime message
	MimeMessage mimeMessage
		= javaMailSender.createMimeMessage();
	MimeMessageHelper mimeMessageHelper;

	try {

		// Setting multipart as true for attachments to
		// be send
		mimeMessageHelper
			= new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom("surajst734@gmail.com");
		mimeMessageHelper.setTo(details.getRecipient());
		mimeMessageHelper.setText(details.getMsgBody());
		mimeMessageHelper.setSubject(
			details.getSubject());

		// Adding the attachment
		FileSystemResource file
			= new FileSystemResource(
				new File(details.getAttachment()));

		mimeMessageHelper.addAttachment(
			file.getFilename(), file);

		// Sending the mail
		javaMailSender.send(mimeMessage);
		return "Mail sent Successfully";
	}

	// Catch block to handle MessagingException
	catch (MessagingException e) {

		// Display message when exception occurred
		return "Error while sending mail!!!";
	}
}
}
//otp, attachment, email, first_name, last_name, msg_body, phone_number, recipient, subject)


