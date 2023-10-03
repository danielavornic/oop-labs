package lab1.src.operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import lab1.src.Main;
import lab1.src.logger.Logger;
import lab1.src.menu.MenuConstants;
import lab1.src.university.*;

public class BatchOperations {
  private static University university = Main.getUniversity();
  private static Logger logger = Main.getLogger();

  public static void batchEnrollStudents() {
    try (BufferedReader br = new BufferedReader(new FileReader(MenuConstants.ENROLLED_STUDENTS_PATH))) {
      br.lines().map(line -> line.split(","))
          .filter(data -> data.length >= 6)
          .forEach(data -> {
            String firstName = data[0].trim();
            String lastName = data[1].trim();
            String email = data[2].trim();
            LocalDate enrollmentDate = LocalDate.parse(data[3].trim());
            LocalDate dateOfBirth = LocalDate.parse(data[4].trim());
            String facultyAbbreviation = data[5].trim();
            Faculty faculty = university.getFacultyByAbbreviation(facultyAbbreviation);

            if (faculty != null) {
              Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, false);
              if (GeneralOperations.findStudentByEmail(faculty, email) == null) {
                faculty.getStudents().add(student);
                logger.logStudentCreation(student, facultyAbbreviation);

                System.out.println("Enrolled student: " + firstName + " " + lastName);
              } else {
                System.out.println("Student with email " + email + " already enrolled.");
              }
            } else {
              System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
            }
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void batchGraduateStudents() {
    try (BufferedReader br = new BufferedReader(new FileReader(MenuConstants.GRADUATED_STUDENTS_PATH))) {
      br.lines().forEach(email -> {
        Student student = university.getFaculties().stream()
            .flatMap(faculty -> faculty.getStudents().stream())
            .filter(s -> s.getEmail().equals(email.trim()))
            .findFirst()
            .orElse(null);
        if (student != null) {
          Faculty faculty = university.getFacultyByStudentEmail(email);

          if (!student.getIsGraduated()) {
            student.setIsGraduated(true);
            logger.logStudentGraduation(student, faculty.getAbbreviation());

            System.out.println("Graduated student: " + student.getFullName());
          } else {
            System.out.println("Student with email " + email + " has already graduated.");
          }
        } else {
          System.out.println("Student with email " + email + " not found.");
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
