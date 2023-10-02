package lab1.src;

import java.util.Scanner;

public class Menu {
  private static Scanner scanner = new Scanner(System.in);
  private static University university;

  public static University getUniversity() {
    return university;
  }

  public static void setUniversity(University uni) {
    university = uni;
  }

  private static final String MAIN_MENU_TITLE = "TUM SMS Main Menu";
  private static final String[] MAIN_MENU_OPTIONS = {
      "1 - General operations",
      "2 - Faculty operations",
      "q - Quit"
  };

  private static final String GENERAL_MENU_TITLE = "TUM SMS General Operations";
  private static final String[] GENERAL_MENU_OPTIONS = {
      "1 - Create faculty",
      "2 - Search student and show faculty",
      "3 - Display faculties",
      "4 - Display all faculties of a field",
      "b - Back",
      "q - Quit"
  };

  private static final String FACULTY_MENU_TITLE = "TUM SMS Faculty Operations";
  private static final String[] FACULTY_MENU_OPTIONS = {
      "1 - Create student",
      "2 - Batch enroll students",
      "3 - Graduate student",
      "4 - Batch graduate students",
      "5 - Display enrolled students",
      "6 - Display graduated students",
      "8 - Check if student belongs to faculty",
      "b - Back",
      "q - Quit"
  };

  /*
   * Display
   * read
   * evaluate
   * 
   * 
   */

  /*
   * appliction.jsvs
   * 
   * private activeMenu: Menu
   * 
   * constructor(generalOp, menuOp, facultyOp)
   * {
   * this.gener = gener
   * ..
   * this.avtiveMenu = menuOp
   * }
   * 
   * while loop {
   * this.activeMenu.displayMenuOptions
   * this.activeMenu.takeInput
   * }this.activeMenu.evaluateInput
   * 
   */

  public static void handleMenuOperations() {
    MenuPrinter.displayMenuOptions(MAIN_MENU_OPTIONS, MAIN_MENU_TITLE);
    String input = scanner.nextLine();

    while (!input.equals("q")) {
      switch (input) {
        case "1":
          handleGeneralOperations();
          break;
        case "2":
          handleFacultyOperations();
          break;
        default:
          System.out.println("Invalid input. Please try again.");
          break;
      }

      input = scanner.nextLine();
    }

    FileManager.saveUniversityData(university);
    scanner.close();
    System.exit(0);
  }

  public static void handleGeneralOperations() {
    MenuPrinter.displayMenuOptions(GENERAL_MENU_OPTIONS, GENERAL_MENU_TITLE);
    String input = scanner.nextLine();

    while (!input.equals("q")) {
      switch (input) {
        case "1":
          GeneralMenu.createFaculty();
          break;
        case "2":
          GeneralMenu.searchStudentAndShowFaculty();
          break;
        case "3":
          GeneralMenu.displayFaculties();
          break;
        case "4":
          GeneralMenu.displayFacultiesByStudyField();
          break;
        case "b":
          // TODO fix infite recursive function call
          handleMenuOperations();
          return;
        default:
          System.out.println("Invalid input. Please try again.");
          break;
      }

      System.out.println();
      System.out.println("Do you want to continue with general operations? (y/n)");
      String continueInput = scanner.nextLine();

      while (!continueInput.equals("y") && !continueInput.equals("n")) {
        System.out.println("Invalid input. Please try again.");
        System.out.println("Do you want to continue? (y/n)");
        continueInput = scanner.nextLine();
      }

      if (continueInput.equals("n")) {
        handleMenuOperations();
        return;
      }

      handleGeneralOperations();
    }

    FileManager.saveUniversityData(university);
    System.exit(0);
  }

  public static void handleFacultyOperations() {
    MenuPrinter.displayMenuOptions(FACULTY_MENU_OPTIONS, FACULTY_MENU_TITLE);
    String input = scanner.nextLine();

    while (!input.equals("q")) {
      switch (input) {
        case "1":
          FacultyMenu.createStudent();
          break;
        case "2":
          FacultyMenu.batchEnrollStudents();
          break;
        case "3":
          FacultyMenu.graduateStudent();
          break;
        case "4":
          FacultyMenu.batchGraduateStudents();
          break;
        case "5":
          FacultyMenu.displayStudents();
          break;
        case "6":
          FacultyMenu.displayGraduatedStudents();
          break;
        case "8":
          FacultyMenu.checkIfStudentBelongsToFaculty();
          break;
        case "b":
          handleMenuOperations();
          return;
        default:
          System.out.println("Invalid input. Please try again.");
          break;
      }

      System.out.println();
      System.out.println("Do you want to continue with faculty operations? (y/n)");
      String continueInput = scanner.nextLine();

      while (!continueInput.equals("y") && !continueInput.equals("n")) {
        System.out.println("Invalid input. Please try again.");
        System.out.println("Do you want to continue? (y/n)");
        continueInput = scanner.nextLine();
      }

      if (continueInput.equals("n")) {
        handleMenuOperations();
        return;
      }

      handleFacultyOperations();
    }

    FileManager.saveUniversityData(university);
    System.exit(0);
  }

  public static void main(String[] args) {
    try {
      university = FileManager.loadUniversityData();
      if (university == null) {
        Menu.setUniversity(new University());
      } else {
        Menu.setUniversity(university);
      }
    } catch (Exception e) {
      Menu.setUniversity(new University());
    }

    handleMenuOperations();

    scanner.close();
  }
}
