package lab1.src.university;

import java.time.LocalDate;

public class Student {
  private String firstName;
  private String lastName;
  private String email;
  private LocalDate enrollmentDate;
  private LocalDate dateOfBirth;
  private boolean isGraduated;

  public Student(String firstName, String lastName, String email, LocalDate enrollmentDate, LocalDate dateOfBirth,
      boolean isGraduated) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.enrollmentDate = enrollmentDate;
    this.dateOfBirth = dateOfBirth;
    this.isGraduated = isGraduated;
  }

  public String getEmail() {
    return email;
  }

  public boolean getIsGraduated() {
    return isGraduated;
  }

  public String getFirstName() {
    return firstName;
  }

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
