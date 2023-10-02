package lab1.src.university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class University implements Serializable {
  private List<Faculty> faculties;

  public University() {
    this.faculties = new ArrayList<>();
  }

  public List<Faculty> getFaculties() {
    return faculties;
  }

  public void addFaculty(Faculty faculty) {
    faculties.add(faculty);
    System.out.println("Faculty created successfully!");
  }

  public Faculty getFacultyByAbbreviation(String abbreviation) {
    return faculties.stream()
        .filter(faculty -> faculty.getAbbreviation().equals(abbreviation))
        .findFirst()
        .orElse(null);
  }

  public Faculty getFacultyByStudentEmail(String email) {
    return faculties.stream()
        .filter(faculty -> faculty.getStudents().stream().anyMatch(student -> student.getEmail().equals(email)))
        .findFirst()
        .orElse(null);
  }

  public void displayFaculties() {
    if (faculties.isEmpty()) {
      System.out.println("There are no faculties.");
      return;
    }

    System.out.println("Faculties: ");
    faculties.forEach(faculty -> System.out.println(faculty.getName() + " (" + faculty.getAbbreviation() + ")"));
  }

  public void displayFacultyByStudyField(StudyField studyField) {
    if (faculties.isEmpty()) {
      System.out.println("There are no faculties.");
      return;
    }

    List<Faculty> resFaculties = new ArrayList<>();

    for (Faculty faculty : faculties) {
      if (studyField.equals(faculty.getStudyField())) {
        resFaculties.add(faculty);
      }
    }

    if (resFaculties.isEmpty()) {
      System.out.println("There are no faculties with study field " + studyField + ".");
      return;
    }

    System.out.println("Faculties with study field " + studyField + ": ");
    for (int i = 0; i < resFaculties.size(); i++) {
      System.out
          .println((i + 1) + ") " + resFaculties.get(i).getName() + " (" + resFaculties.get(i).getAbbreviation() + ")");
    }
  }

  public void displayStudents() {
    List<Student> enrolledStudents = faculties.stream()
        .flatMap(faculty -> faculty.getStudents().stream())
        .collect(Collectors.toList());

    if (enrolledStudents.isEmpty()) {
      System.out.println("There are no students.");
      return;
    }

    System.out.println(enrolledStudents.size() + " enrolled students: ");
    enrolledStudents.forEach(student -> {
      Faculty faculty = getFacultyByStudentEmail(student.getEmail());
      String facultyAbbreviation = (faculty != null) ? faculty.getAbbreviation() : "N/A";
      System.out.println(student.getFullName() + " (" + student.getEmail() + "), " + facultyAbbreviation);
    });
  }

  public void displayGraduatedStudents() {
    List<Student> graduatedStudents = faculties.stream()
        .flatMap(faculty -> faculty.getStudents().stream())
        .filter(Student::getIsGraduated)
        .collect(Collectors.toList());

    if (graduatedStudents.isEmpty()) {
      System.out.println("There are no graduated students.");
      return;
    }

    System.out.println("Graduated students: ");
    graduatedStudents.forEach(student -> {
      Faculty faculty = getFacultyByStudentEmail(student.getEmail());
      String facultyAbbreviation = (faculty != null) ? faculty.getAbbreviation() : "N/A";
      System.out.println(student.getFullName() + " (" + student.getEmail() + "), " + facultyAbbreviation);
    });
  }
}