package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;


@Controller
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	

@CrossOrigin
@PostMapping("/addStudent")
public ResponseEntity<Object> addStudent(@RequestBody Student std)
{
//Student stud1 = new Student();
//stud1.setStudent_name(std.student_name);
//stud1.setStudent_class(8);
	
	std.setStudent_name(std.getStudent_name());
	std.setStudent_class(std.getStudent_class());	
	studentRepository.save(std);

return new ResponseEntity<Object>(HttpStatus.OK);

}


@CrossOrigin
@GetMapping("/getStudent")
public ResponseEntity<Object>getStudent(){
	
	List ls = studentRepository.findAll();
	studentRepository.getById(8);
	return new ResponseEntity<Object>(ls,HttpStatus.OK);
}


@CrossOrigin
@PostMapping("/deleteStudent")
public ResponseEntity<Object> deleteStudent(@RequestBody Student std)
{
	studentRepository.deleteById(std.getStudent_id());
	
	return new ResponseEntity<Object>(HttpStatus.OK);
}

@CrossOrigin
@PutMapping("/updateStudent")
	public ResponseEntity<Object>updateStudent(@RequestBody Student std)
	{
	
	studentRepository.getById(std.getStudent_id());
		if(studentRepository.getById(std.getStudent_id())!=null) 
		{
			studentRepository.getById(std.getStudent_id()).setStudent_name(std.getStudent_name());
			studentRepository.getById(std.getStudent_id()).setStudent_class(std.getStudent_class());
		}
			return new ResponseEntity<>(studentRepository.save(studentRepository.getById(std.getStudent_id())),HttpStatus.OK); 	
	}
}
