package lab1.src.university;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
  private String firstName;
  private String lastName;
  private String email;
  private LocalDate enrollmentDate;
  private LocalDate dateOfBirth;
  private boolean isGraduated;

  public Student(String firstName, String lastName, String email, LocalDate enrollmentDate, LocalDate dateOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.enrollmentDate = enrollmentDate;
    this.dateOfBirth = dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public boolean getIsGraduated() {
    return isGraduated;
  }

  // get first name
  public String getFirstName() {
    return firstName;
  }

  // get last name
  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public LocalDate getBirthDate() {
    return dateOfBirth;
  }

  public LocalDate getEnrollmentDate() {
    return enrollmentDate;
  }

  public void setIsGraduated(boolean isGraduated) {
    this.isGraduated = isGraduated;
  }
}
