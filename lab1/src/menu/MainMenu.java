package lab1.src.menu;

import lab1.src.Main;
import lab1.src.store.FileManager;

public class MainMenu {
  private static final String MAIN_MENU_TITLE = "TUM SMS Main Menu";
  private static final String[] MAIN_MENU_OPTIONS = {
      "1 - General operations",
      "2 - Faculty operations",
      "q - Quit"
  };

  public static void run() {
    MenuPrinter.displayMenuOptions(MAIN_MENU_OPTIONS, MAIN_MENU_TITLE);
    String input = InputHandler.getStringInput("Choose an option: ");

    switch (input) {
      case "1":
        GeneralMenu.run();
        break;
      case "2":
        FacultyMenu.run();
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
      System.out.println("Exiting...");
      FileManager.saveUniversityData(Main.getUniversity());
      System.exit(0);
    }
  }
}
