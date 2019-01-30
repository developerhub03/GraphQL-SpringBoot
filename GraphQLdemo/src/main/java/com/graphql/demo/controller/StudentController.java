package com.graphql.demo.controller;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

import com.graphql.demo.service.StudentService;
import graphql.ExecutionResult;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/student")
@RestController
public class StudentController {

  @Autowired
  StudentService studentService;

  @RequestMapping("/details")
  @POST
  public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
    ExecutionResult execute = studentService.getGraphQL().execute(query);

    return new ResponseEntity<>(execute, HttpStatus.OK);
  }

//  @RequestMapping("/create")
//  @POST
//  public ResponseEntity<Object> register(@RequestBody String mutation){
//    ExecutionResult executionResult = studentService.getGraphQL().execute(mutation);
//
//    // Check if there are errors
//    if(!executionResult.getErrors().isEmpty()){
//      return new ResponseEntity<>(executionResult.getErrors().get(0).getMessage(), HttpStatus.UNAUTHORIZED);
//    }
//
//    return new ResponseEntity<>(executionResult, HttpStatus.OK);
//  }

}
