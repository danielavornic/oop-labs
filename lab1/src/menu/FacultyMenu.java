package lab1.src.menu;

import lab1.src.operations.*;

public class FacultyMenu extends Menu {
  protected String getMenuTitle() {
    return "TUM SMS Faculty Operations";
  }

  protected String[] getMenuOptions() {
    return new String[] {
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
  }

  @Override
  public void run() {
    displayMenu();
    String input = InputHandler.getStringInput("Choose an option: ");

    switch (input) {
      case "1":
        StudentOperations.createStudent();
        break;
      case "2":
        BatchOperations.batchEnrollStudents();
        break;
      case "3":
        StudentOperations.graduateStudent();
        break;
      case "4":
        BatchOperations.batchGraduateStudents();
        break;
      case "5":
        FacultyOperations.displayStudents();
        break;
      case "6":
        FacultyOperations.displayGraduatedStudents();
        break;
      case "8":
        FacultyOperations.checkIfStudentBelongsToFaculty();
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