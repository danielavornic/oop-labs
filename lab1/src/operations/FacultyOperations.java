package lab1.src.operations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lab1.src.Main;
import lab1.src.menu.*;
import lab1.src.university.*;

public class FacultyOperations {
  private static University university = Main.getUniversity();

  public static void checkIfStudentBelongsToFaculty() {
    String email = InputHandler.getStringInput("Student email: ");
    GeneralOperations.displayFaculties();

    int facultyIndex;
    Faculty faculty;

    do {
      facultyIndex = InputHandler.getInputInt("Faculty index: ") - 1;
      if (facultyIndex >= 0 && facultyIndex < university.getFaculties().size()) {
        faculty = university.getFaculties().get(facultyIndex);
        Optional<Student> matchingStudent = faculty.getStudents().stream()
            .filter(student -> student.getEmail().equals(email))
            .findFirst();

        if (matchingStudent.isPresent()) {
          System.out.println("Student with email " + email + " belongs to faculty " + faculty.getName() + ".");
        } else {
          System.out.println("Student with email " + email + " does not belong to faculty " + faculty.getName() + ".");
        }

        return;
      }

      System.out.println(MenuConstants.INVALID_FACULTY_INDEX_MESSAGE);
    } while (true);
  }

  public static void displayStudents() {
    List<Student> enrolledStudents = university.getFaculties().stream()
        .flatMap(faculty -> faculty.getStudents().stream())
        .filter(student -> !student.getIsGraduated())
        .collect(Collectors.toList());

    if (enrolledStudents.isEmpty()) {
      System.out.println("There are no students.");
      return;
    }

    System.out.println("Enrolled students: ");
    enrolledStudents.forEach(student -> {
      Faculty faculty = university.getFacultyByStudentEmail(student.getEmail());
      String facultyAbbreviation = (faculty != null) ? faculty.getAbbreviation() : "N/A";
      System.out.println(student.getFullName() + " (" + student.getEmail() + "), " + facultyAbbreviation);
    });
  }

  public static void displayGraduatedStudents() {
    List<Student> graduatedStudents = university.getFaculties().stream()
        .flatMap(faculty -> faculty.getStudents().stream())
        .filter(Student::getIsGraduated)
        .collect(Collectors.toList());

    if (graduatedStudents.isEmpty()) {
      System.out.println("There are no graduated students.");
      return;
    }

    System.out.println("Graduated students: ");
    graduatedStudents.forEach(student -> {
      Faculty faculty = university.getFacultyByStudentEmail(student.getEmail());
      String facultyAbbreviation = (faculty != null) ? faculty.getAbbreviation() : "N/A";
      System.out.println(student.getFullName() + " (" + student.getEmail() + "), " + facultyAbbreviation);
    });
  }
}
