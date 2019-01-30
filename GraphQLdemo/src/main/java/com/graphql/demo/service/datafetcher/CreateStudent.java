package com.graphql.demo.service.datafetcher;

import com.graphql.demo.model.StudentDetails;
import com.graphql.demo.repository.StudentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateStudent implements DataFetcher<StudentDetails> {

  @Autowired
  StudentRepository studentRepository;

  @Override
  public StudentDetails get(DataFetchingEnvironment dataFetchingEnvironment) {

    String firstName = dataFetchingEnvironment.getArgument("firstName");
    String lastName = dataFetchingEnvironment.getArgument("lastName");
    String schoolName = dataFetchingEnvironment.getArgument("schoolName");
    String grade = dataFetchingEnvironment.getArgument("grade");
    String marks = dataFetchingEnvironment.getArgument("marks");
    return studentRepository
        .save(new StudentDetails(firstName, lastName, grade, marks, schoolName));

  }
}
