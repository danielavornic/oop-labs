package lab1.src.operations;

import java.time.LocalDate;

import lab1.src.Main;
import lab1.src.menu.InputHandler;
import lab1.src.menu.MenuConstants;
import lab1.src.university.*;

public class StudentOperations {
  private static University university = Main.getUniversity();

  public static void createStudent() {
    System.out.println("Creating student...");
    String firstName = InputHandler.getStringInput("First name: ");
    String lastName = InputHandler.getStringInput("Last name: ");
    String email = InputHandler.getStringInput("Email: ");
    LocalDate enrollmentDate = InputHandler.getDateInput("Enrollment date: ");
    LocalDate dateOfBirth = InputHandler.getDateInput("Date of birth: ");
    Faculty faculty = chooseFaculty();
    if (faculty != null) {
      Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, false);
      faculty.getStudents().add(student);
      System.out.println("Student created successfully!");
    }
  }

  public static void graduateStudent() {
    String email = InputHandler.getStringInput("Student email: ");
    Faculty faculty = university.getFacultyByStudentEmail(email);
    if (faculty == null) {
      System.out.println("Student with email " + email + " not found.");
    } else {
      Student student = findStudentByEmail(faculty, email);
      if (student != null) {
        if (!student.getIsGraduated()) {
          student.setIsGraduated(true);
          System.out.println("Student graduated successfully!");
        } else {
          System.out.println("Student with email " + email + " has already graduated.");
        }
      } else {
        System.out.println("Student with email " + email + " not found.");
      }
    }
  }

  private static Student findStudentByEmail(Faculty faculty, String email) {
    return faculty.getStudents().stream().filter(student -> student.getEmail().equals(email)).findFirst().orElse(null);
  }

  private static Faculty chooseFaculty() {
    GeneralOperations.displayFaculties();

    int facultyIndex = InputHandler.getInputInt("Faculty index: ") - 1;
    if (facultyIndex >= 0 && facultyIndex < university.getFaculties().size()) {
      return university.getFaculties().get(facultyIndex);
    }

    System.out.println(MenuConstants.INVALID_FACULTY_INDEX_MESSAGE);
    return null;
  }
}
