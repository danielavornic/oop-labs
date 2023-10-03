package lab1.src.university;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class University {
  private ArrayList<Faculty> faculties;

  public University() {
    this.faculties = new ArrayList<>();
  }

  public ArrayList<Faculty> getFaculties() {
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

  public List<Faculty> getFacultiesByStudyField(StudyField studyField) {
    List<Faculty> resFaculties = faculties.stream()
        .filter(faculty -> studyField.equals(faculty.getStudyField()))
        .collect(Collectors.toList());

    return resFaculties;
  }
}
