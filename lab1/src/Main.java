package lab1.src;

import lab1.src.logger.Logger;
import lab1.src.menu.*;
import lab1.src.store.*;
import lab1.src.university.University;

public class Main {
  private static University university;
  private static Menu activeMenu;
  private static Logger logger;

  public static University getUniversity() {
    return university;
  }

  public static Menu getMenu() {
    return activeMenu;
  }

  public static Logger getLogger() {
    return logger;
  }

  public static void setUniversity(University uni) {
    university = uni;
  }

  public static void setMenu(Menu menu) {
    activeMenu = menu;
    activeMenu.run();
  }

  public static void main(String[] args) {
    try {
      university = UniFileManager.loadUniversityData();
      if (university == null) {
        university = new University();
      }
    } catch (Exception e) {
      university = new University();
    }

    System.out.print("\033[H\033[2J");
    String username = InputHandler.getStringInput("Enter your username: ");
    logger = new Logger(username);

    Menu menu = new MainMenu();
    menu.run();
  }
}
