package lab1.src;

import lab1.src.menu.*;
import lab1.src.store.FileManager;
import lab1.src.university.University;

public class Main {
  private static University university;
  private static Menu activeMenu;

  public static University getUniversity() {
    return university;
  }

  public static void setUniversity(University uni) {
    university = uni;
  }

  public static void setMenu(Menu menu) {
    activeMenu = menu;
    activeMenu.run();
  }

  public static Menu getMenu() {
    return activeMenu;
  }

  public static void main(String[] args) {
    try {
      university = FileManager.loadUniversityData();
      if (university == null) {
        university = new University();
      }
    } catch (Exception e) {
      university = new University();
    }

    Menu menu = new MainMenu();
    menu.run();
  }
}
