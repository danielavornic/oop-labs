package lab1.src.menu;

import lab1.src.Main;

public class MainMenu extends Menu {
  protected String getMenuTitle() {
    return "TUM SMS Main Menu";
  }

  protected String[] getMenuOptions() {
    return new String[] {
        "1 - General operations",
        "2 - Faculty operations",
        "q - Quit"
    };
  }

  @Override
  public void run() {
    displayMenu();
    String input = InputHandler.getStringInput("Choose an option: ");

    switch (input) {
      case "1":
        Main.setMenu(new GeneralMenu());
        Main.getMenu().run();
        break;
      case "2":
        Main.setMenu(new FacultyMenu());
        Main.getMenu().run();
        break;
      case "q":
        exit();
        break;
      default:
        System.out.println(INVALID_INPUT_MESSAGE);
        break;
    }

    continueOrExit();
  }
}
