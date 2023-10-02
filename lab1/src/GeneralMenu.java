package lab1.src;

import java.util.ArrayList;

public class GeneralMenu {
  private static University university = Menu.getUniversity();

  private static final String INVALID_INDEX_MESSAGE = "Invalid study field index. Please choose a valid index.";
  private static final String STUDENT_NOT_FOUND_MESSAGE = "Student not found.";

  public static StudyField getStudyFieldSelection() {
    System.out.println("Study fields: ");
    for (int i = 0; i < StudyField.values().length; i++) {
      System.out.println((i + 1) + ") " + StudyField.values()[i]);
    }
    int studyFieldIndex = InputHandler.getInputInt("Study field index: ") - 1;
    while (!isStudyFieldIndexValid(studyFieldIndex)) {
      System.out.println(INVALID_INDEX_MESSAGE);
      studyFieldIndex = InputHandler.getInputInt("Study field index: ") - 1;
    }
    return StudyField.values()[studyFieldIndex];
  }

  public static void createFaculty() {
    String name = InputHandler.getStringInput("Faculty name: ");
    String abbreviation = InputHandler.getStringInput("Faculty abbreviation: ");
    StudyField studyField = getStudyFieldSelection();
    Faculty faculty = new Faculty(name, abbreviation, new ArrayList<>(), studyField);
    university.addFaculty(faculty);
    Menu.setUniversity(university);
  }

  public static void searchStudentAndShowFaculty() {
    String email = InputHandler.getStringInput("Student email: ");
    Faculty faculty = university.getFacultyByStudentEmail(email);
    if (faculty == null) {
      System.out.println(STUDENT_NOT_FOUND_MESSAGE);
    } else {
      Student student = findStudentByEmail(faculty, email);
      if (student != null) {
        System.out.println("Student " + student.getFullName() + " found in faculty " + faculty.getName());
      } else {
        System.out.println(STUDENT_NOT_FOUND_MESSAGE);
      }
    }
  }

  private static boolean isStudyFieldIndexValid(int index) {
    return index >= 0 && index < StudyField.values().length;
  }

  private static Student findStudentByEmail(Faculty faculty, String email) {
    for (Student student : faculty.getStudents()) {
      if (student.getEmail().equals(email)) {
        return student;
      }
    }
    return null;
  }

  public static void displayFaculties() {
    university.displayFaculties();
  }

  public static void displayFacultiesByStudyField() {
    StudyField studyField = getStudyFieldSelection();
    university.displayFacultyByStudyField(studyField);
  }
}
