package lab1.src.university;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
  private String name;
  private String abbreviation;
  public List<Student> students;
  private StudyField studyField;

  public Faculty(String name, String abbreviation, List<Student> students, StudyField studyField) {
    this.name = name;
    this.abbreviation = abbreviation;
    this.students = students;
    this.studyField = studyField;
  }

  public String getName() {
    return name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public StudyField getStudyField() {
    return studyField;
  }

  public List<Student> getStudents() {
    if (students == null) {
      students = new ArrayList<>();
    }

    return students;
  }

  public Student getStudentByEmail(String email) {
    return students.stream().filter(student -> student.getEmail().equals(email)).findFirst().orElse(null);
  }

  public void addStudent(Student student) {
    if (students == null) {
      students = new ArrayList<>();
    }

    if (!students.contains(student)) {
      students.add(student);
    }
  }
}
