package lab1.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Faculty implements Serializable {
  private String name;
  private String abbreviation;
  private static List<Student> students;
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

  public static Student getStudentByEmail(String email) {
    for (Student student : students) {
      if (student.getEmail().equals(email)) {
        return student;
      }
    }

    return null;
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
