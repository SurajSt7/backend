package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="student_id")
private int student_id;
@Column(name="student_name")
private String student_name;
@Column(name="student_class")
private int student_class;
public int getStudent_id() {
	return student_id;
}

public void setStudent_id(int student_id) {
	this.student_id = student_id;
}
public String getStudent_name() {
	return student_name;
}
public void setStudent_name(String student_name) {
	this.student_name = student_name;
}
public int getStudent_class() {
	return student_class;
}
public void setStudent_class(int student_class) {
	this.student_class = student_class;
}
public Student() {
	super();
	// TODO Auto-generated constructor stub
}
public Student(int student_id, String student_name, int student_class) {
	super();
	this.student_id = student_id;
	this.student_name = student_name;
	this.student_class = student_class;
}
@Override
public String toString() {
	return "Student [student_id=" + student_id + ", student_name=" + student_name + ","
			+ " student_class=" + student_class
			+ "]";
}
}
