package lab1.src;

import lab1.src.menu.MainMenu;
import lab1.src.store.FileManager;
import lab1.src.university.University;

public class Main {
  private static University university;

  public static University getUniversity() {
    return university;
  }

  public static void setUniversity(University uni) {
    university = uni;
  }

  public static void main(String[] args) {
    try {
      university = FileManager.loadUniversityData();
      if (university == null) {
        Main.setUniversity(new University());
      } else {
        Main.setUniversity(university);
      }
    } catch (Exception e) {
      Main.setUniversity(new University());
    }

    MainMenu.run();
  }
}
