package com.example.demo.service;
import java.io.IOException;
import java.util.Base64;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Register;
import com.example.demo.repository.RegisterRepository;

@Service
public class Image{
	@Autowired
	private RegisterRepository registerRepository;
	
	public void uploadImage(MultipartFile file) {
		Register rst = new Register();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());	
		if(fileName.contains(".."))
				{
			System.out.println("not a valid file");
				}
		try {
			rst.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		registerRepository.save(rst);
				}
	
	}