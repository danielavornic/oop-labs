package lab1.src;

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

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public void setIsGraduated(boolean isGraduated) {
    this.isGraduated = isGraduated;
  }
}
