package com.example.demo.repository;

//Demo Package
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
	
	
}
