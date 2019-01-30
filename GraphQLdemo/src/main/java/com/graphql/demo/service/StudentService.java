package com.graphql.demo.service;

import com.graphql.demo.model.StudentDetails;
import com.graphql.demo.repository.StudentRepository;
import com.graphql.demo.service.datafetcher.AllStudentDetailsFetcher;
import com.graphql.demo.service.datafetcher.CreateStudent;
import com.graphql.demo.service.datafetcher.StudentDetailFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;

  @Value("classpath:student.graphql")
  Resource resource;

  private GraphQL graphQL;

  @Autowired
  private AllStudentDetailsFetcher allStudentDetailsFetcher;

  @Autowired
  private StudentDetailFetcher studentDetailFetcher;

  @Autowired
  private CreateStudent createStudent;

  // load schema at application start up
  @PostConstruct
  private void loadSchema() throws IOException {

    //Load Books into the Book Repository
    loadDataIntoHSQL();

    // get the schema
    File schemaFile = resource.getFile();
    // parse schema
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
    RuntimeWiring wiring = buildRuntimeWiring();
    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
    graphQL = GraphQL.newGraphQL(schema).build();
  }

  private void loadDataIntoHSQL() {
    studentRepository.deleteAll();

    Stream.of(
        new StudentDetails("Ram", "K", "10", "498", "KTS"),
        new StudentDetails("Sai", "K", "10", "499", "KTS"),
        new StudentDetails("Sai", "K", "9", "498", "KTS"),
        new StudentDetails("Nattu", "K", "9", "498", "KTS")
    ).forEach(student -> {
      studentRepository.save(student);
    });
  }

  private RuntimeWiring buildRuntimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type("Query", typeWiring -> typeWiring
            .dataFetcher("allStudents", allStudentDetailsFetcher)
            .dataFetcher("student", studentDetailFetcher))
        .type("Mutation", typeWiring -> typeWiring
            .dataFetcher("createStudent", createStudent))
        .build();
  }


  public GraphQL getGraphQL() {
    return graphQL;
  }
}