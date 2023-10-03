package lab1.src.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lab1.src.Main;
import lab1.src.logger.Logger;
import lab1.src.menu.InputHandler;
import lab1.src.menu.MenuConstants;
import lab1.src.university.*;

public class GeneralOperations {
  private static University university = Main.getUniversity();
  private static Logger logger = Main.getLogger();

  public static void displayFaculties() {
    if (university.getFaculties().isEmpty()) {
      System.out.println("There are no faculties.");
      return;
    }

    System.out.println("Faculties: ");
    IntStream.range(0, university.getFaculties().size())
        .forEach(index -> {
          Faculty faculty = university.getFaculties().get(index);
          System.out.println((index + 1) + ") " + faculty.getName() + " (" + faculty.getAbbreviation() + ")");
        });
  }

  public static void searchStudentAndShowFaculty() {
    String email = InputHandler.getStringInput("Student email: ");
    Faculty faculty = university.getFacultyByStudentEmail(email);
    if (faculty == null) {
      System.out.println(MenuConstants.STUDENT_NOT_FOUND_MESSAGE);
      return;
    }

    Student student = findStudentByEmail(faculty, email);
    if (student == null) {
      System.out.println(MenuConstants.STUDENT_NOT_FOUND_MESSAGE);
      return;
    }

    System.out.println("Student " + student.getFullName() + " found in faculty " + faculty.getName() + ".");
  }

  public static Student findStudentByEmail(Faculty faculty, String email) {
    return faculty.getStudents().stream().filter(student -> student.getEmail().equals(email)).findFirst().orElse(null);
  }

  public static StudyField getStudyFieldSelection() {
    System.out.println("Study fields: ");
    for (int i = 0; i < StudyField.values().length; i++) {
      System.out.println((i + 1) + ") " + StudyField.values()[i]);
    }
    int studyFieldIndex = InputHandler.getInputInt("Study field index: ") - 1;
    while (!isStudyFieldIndexValid(studyFieldIndex)) {
      System.out.println(MenuConstants.INVALID_ST_INDEX_MESSAGE);
      studyFieldIndex = InputHandler.getInputInt("Study field index: ") - 1;
    }
    return StudyField.values()[studyFieldIndex];
  }

  private static boolean isStudyFieldIndexValid(int index) {
    return index >= 0 && index < StudyField.values().length;
  }

  public static void createFaculty() {
    String name = InputHandler.getStringInput("Faculty name: ");
    String abbreviation = InputHandler.getStringInput("Faculty abbreviation: ");
    StudyField studyField = getStudyFieldSelection();
    Faculty faculty = new Faculty(name, abbreviation, new ArrayList<>(), studyField);
    university.addFaculty(faculty);
    Main.setUniversity(university);

    logger.logFacultyCreation(faculty);
  }

  public static void displayFacultiesByStudyField() {
    StudyField studyField = getStudyFieldSelection();
    List<Faculty> f = university.getFacultiesByStudyField(studyField);

    if (f.isEmpty()) {
      System.out.println("There are no faculties with study field " + studyField + ".");
      return;
    }

    System.out.println("Faculties with study field " + studyField + ": ");
    f.forEach(faculty -> System.out.println(faculty.getName() + " (" +
        faculty.getAbbreviation() + ")"));
  }
}
