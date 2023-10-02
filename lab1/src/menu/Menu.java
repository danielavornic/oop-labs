package lab1.src.menu;

import lab1.src.Main;
import lab1.src.store.FileManager;
import lab1.src.university.University;

public abstract class Menu {
  protected static University university = Main.getUniversity();

  public abstract void run();

  protected void exit() {
    System.out.println("Exiting...");
    FileManager.saveUniversityData(Main.getUniversity());
    System.exit(0);
  }
}
