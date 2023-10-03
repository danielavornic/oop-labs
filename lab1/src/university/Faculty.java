package lab1.src.university;

import java.util.ArrayList;

public class Faculty {
  private String name;
  private String abbreviation;
  public ArrayList<Student> students;
  private StudyField studyField;

  public Faculty(String name, String abbreviation, ArrayList<Student> students, StudyField studyField) {
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

  public ArrayList<Student> getStudents() {
    if (students == null) {
      students = new ArrayList<>();
    }

    return students;
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
