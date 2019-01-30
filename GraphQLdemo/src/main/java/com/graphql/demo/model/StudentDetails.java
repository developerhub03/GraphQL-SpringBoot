package com.graphql.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table
@Entity
public class StudentDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long studentID;
  private String firstName;
  private String lastName;
  private String grade;
  private String marks;
  private String schoolName;

  public StudentDetails(String firstName, String lastName, String grade, String marks,
      String schoolName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.grade = grade;
    this.marks = marks;
    this.schoolName = schoolName;
  }
}
