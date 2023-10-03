package lab1.src.menu;

import lab1.src.Main;
import lab1.src.store.UniFileManager;
import lab1.src.university.University;

public abstract class Menu {
  protected static University university = Main.getUniversity();

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
    System.out.println();
    System.out.println("Exiting...");
    UniFileManager.saveUniversityData(university);
    InputHandler.closeScanner();
    System.exit(0);
  }

  protected boolean getYesOrNoInput() {
    System.out.println();
    System.out.println("Do you want to continue? (y/n)");
    String input = InputHandler.getStringInput("Choose an option: ");
    return input.equalsIgnoreCase("y");
  }

  protected void continueOrBack() {
    if (getYesOrNoInput()) {
      run();
    } else {
      back();
    }
  }

  protected void continueOrExit() {
    if (getYesOrNoInput()) {
      run();
    } else {
      exit();
    }
  }
}
