package lab1.src.menu;

import java.util.ArrayList;

import lab1.src.Main;
import lab1.src.university.*;

public class GeneralMenu extends Menu {
  protected String getMenuTitle() {
    return "TUM SMS General Operations";
  }

  protected String[] getMenuOptions() {
    return new String[] {
        "1 - Create faculty",
        "2 - Search student and show faculty",
        "3 - Display faculties",
        "4 - Display all faculties of a field",
        "b - Back",
        "q - Quit"
    };
  }

  public void run() {
    displayMenu();
    String input = InputHandler.getStringInput("Choose an option: ");
    switch (input) {
      case "1":
        createFaculty();
        break;
      case "2":
        searchStudentAndShowFaculty();
        break;
      case "3":
        university.displayFaculties();
        break;
      case "4":
        displayFacultiesByStudyField();
        break;
      case "b":
        back();
        break;
      case "q":
        exit();
        break;
      default:
        System.out.println(INVALID_INPUT_MESSAGE);
        break;
    }

    continueOrBack();
  }

  public static StudyField getStudyFieldSelection() {
    System.out.println("Study fields: ");
    for (int i = 0; i < StudyField.values().length; i++) {
      System.out.println((i + 1) + ") " + StudyField.values()[i]);
    }
    int studyFieldIndex = InputHandler.getInputInt("Study field index: ") - 1;
    while (!isStudyFieldIndexValid(studyFieldIndex)) {
      System.out.println(INVALID_ST_INDEX_MESSAGE);
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
    Main.setUniversity(university);
  }

  public static void searchStudentAndShowFaculty() {
    String email = InputHandler.getStringInput("Student email: ");
    Faculty faculty = university.getFacultyByStudentEmail(email);
    if (faculty == null) {
      System.out.println(STUDENT_NOT_FOUND_MESSAGE);
      return;
    }

    Student student = findStudentByEmail(faculty, email);
    if (student == null) {
      System.out.println(STUDENT_NOT_FOUND_MESSAGE);
      return;
    }

    System.out.println("Student " + student.getFullName() + " found in faculty " + faculty.getName());
  }

  private static boolean isStudyFieldIndexValid(int index) {
    return index >= 0 && index < StudyField.values().length;
  }

  private static Student findStudentByEmail(Faculty faculty, String email) {
    return faculty.getStudents().stream().filter(student -> student.getEmail().equals(email)).findFirst().orElse(null);
  }

  public static void displayFacultiesByStudyField() {
    StudyField studyField = getStudyFieldSelection();
    university.displayFacultyByStudyField(studyField);
  }
}
