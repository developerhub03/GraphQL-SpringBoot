package com.graphql.demo.service.datafetcher;

import com.graphql.demo.model.StudentDetails;
import com.graphql.demo.repository.StudentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDetailFetcher implements DataFetcher<Optional<StudentDetails>> {

  @Autowired
  StudentRepository studentRepository;

  @Override
  public Optional<StudentDetails> get(DataFetchingEnvironment dataFetchingEnvironment) {

    Long studentID = dataFetchingEnvironment.getArgument("id");

    return studentRepository.findById(studentID);
  }
}