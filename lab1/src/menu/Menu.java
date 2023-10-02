package lab1.src.menu;

import lab1.src.Main;
import lab1.src.store.FileManager;
import lab1.src.university.University;

public abstract class Menu {
  protected static University university;

  public static String FOLDER_PATH = "/home/vornic/Uni/sem_3/oop/labs/lab1/temp/";
  public static String ENROLLED_STUDENTS_PATH = FOLDER_PATH + "studentsToEnroll.txt";
  public static String GRADUATED_STUDENTS_PATH = FOLDER_PATH + "studentsToGraduate.txt";
  public static String INVALID_INPUT_MESSAGE = "Invalid input. Please choose a valid option.";
  public static String INVALID_ST_INDEX_MESSAGE = "Invalid study field index. Please choose a valid index.";
  public static String INVALID_FACULTY_INDEX_MESSAGE = "Invalid faculty index. Please choose a valid index.";
  public static String STUDENT_NOT_FOUND_MESSAGE = "Student not found.";

  public void setUniversity(University uni) {
    university = uni;
  }

  public abstract void run();

  abstract String getMenuTitle();

  abstract String[] getMenuOptions();

  public void displayMenu() {
    MenuPrinter.displayMenuOptions(getMenuTitle(), getMenuOptions());
  }

  protected void back() {
    Main.setMenu(new MainMenu());
    Main.getMenu().run();
  }

  protected void exit() {
    System.out.println("Exiting...");
    FileManager.saveUniversityData(Main.getUniversity());
    System.exit(0);
  }

  protected void continueOrBack() {
    System.out.println("Do you want to continue? (y/n)");
    String continueInput = InputHandler.getStringInput("Choose an option: ");
    if (continueInput.equals("y")) {
      run();
    } else {
      back();
    }
  }

  protected void continueOrExit() {
    System.out.println("Do you want to continue? (y/n)");
    String continueInput = InputHandler.getStringInput("Choose an option: ");
    if (continueInput.equals("y")) {
      run();
    } else {
      exit();
    }
  }
}
