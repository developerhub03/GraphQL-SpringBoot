package com.graphql.demo.repository;

import com.graphql.demo.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentDetails, Long> {

}
