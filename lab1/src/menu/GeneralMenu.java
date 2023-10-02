package lab1.src.menu;

import java.util.ArrayList;

import lab1.src.Main;
import lab1.src.store.FileManager;
import lab1.src.university.*;

public class GeneralMenu {
  private static University university = Main.getUniversity();

  private static final String INVALID_INDEX_MESSAGE = "Invalid study field index. Please choose a valid index.";
  private static final String STUDENT_NOT_FOUND_MESSAGE = "Student not found.";

  private static final String GENERAL_MENU_TITLE = "TUM SMS General Operations";
  private static final String[] GENERAL_MENU_OPTIONS = {
      "1 - Create faculty",
      "2 - Search student and show faculty",
      "3 - Display faculties",
      "4 - Display all faculties of a field",
      "b - Back",
      "q - Quit"
  };

  public static void run() {
    MenuPrinter.displayMenuOptions(GENERAL_MENU_OPTIONS, GENERAL_MENU_TITLE);
    String input = InputHandler.getStringInput("Choose an option: ");
    switch (input) {
      case "1":
        createFaculty();
        break;
      case "2":
        searchStudentAndShowFaculty();
        break;
      case "3":
        displayFaculties();
        break;
      case "4":
        displayFacultiesByStudyField();
        break;
      case "b":
        MainMenu.run();
        break;
      case "q":
        System.out.println("Exiting...");
        FileManager.saveUniversityData(Main.getUniversity());
        System.exit(0);
        break;
      default:
        System.out.println("Invalid input. Please choose a valid option.");
        break;
    }

    System.out.println("Do you want to continue? (y/n)");
    String continueInput = InputHandler.getStringInput("Choose an option: ");
    if (continueInput.equals("y")) {
      run();
    } else {
      MainMenu.run();
    }
  }

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
    Main.setUniversity(university);
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
