package com.graphql.demo.service.datafetcher;

import com.graphql.demo.model.StudentDetails;
import com.graphql.demo.repository.StudentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllStudentDetailsFetcher implements DataFetcher<List<StudentDetails>> {

  @Autowired
  StudentRepository studentRepository;

  @Override
  public List<StudentDetails> get(DataFetchingEnvironment dataFetchingEnvironment) {
    return studentRepository.findAll();
  }
}