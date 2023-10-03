package lab1.src.menu;

import lab1.src.operations.GeneralOperations;

public class GeneralMenu extends Menu {
  protected String getMenuTitle() {
    return "TUM SMS General Operations";
  }

  protected String[] getMenuOptions() {
    return new String[] {
        "1 - Create faculty",
        "2 - Search student and show faculty",
        "3 - Display faculties",
        "4 - Display all faculties of a field",
        "b - Back",
        "q - Quit"
    };
  }

  public void run() {
    displayMenu();
    String input = InputHandler.getStringInput("Choose an option: ");

    switch (input) {
      case "1":
        GeneralOperations.createFaculty();
        break;
      case "2":
        GeneralOperations.searchStudentAndShowFaculty();
        break;
      case "3":
        GeneralOperations.displayFaculties();
        break;
      case "4":
        GeneralOperations.displayFacultiesByStudyField();
        break;
      case "b":
        back();
        break;
      case "q":
        exit();
        break;
      default:
        System.out.println(MenuConstants.INVALID_INPUT_MESSAGE);
        break;
    }

    continueOrBack();
  }
}
