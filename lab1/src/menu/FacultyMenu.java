package lab1.src.menu;

import lab1.src.university.*;

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
        university.displayStudents();
        break;
      case "6":
        university.displayGraduatedStudents();
        break;
      case "8":
        checkIfStudentBelongsToFaculty();
        break;
      case "b":
        back();
        break;
      case "q":
        exit();
        break;
      default:
        System.out.println(INVALID_INPUT_MESSAGE);
        break;
    }

    continueOrBack();
  }

  public static void checkIfStudentBelongsToFaculty() {
    String email = InputHandler.getStringInput("Student email: ");
    university.displayFaculties();
    int facultyIndex = InputHandler.getInputInt("Faculty index: ") - 1;
    while (facultyIndex < 0 || facultyIndex >= university.getFaculties().size()) {
      System.out.println(INVALID_FACULTY_INDEX_MESSAGE);
      facultyIndex = InputHandler.getInputInt("Faculty index: ");
    }

    Faculty faculty = university.getFaculties().get(facultyIndex);
    for (Student student : faculty.getStudents()) {
      if (student.getEmail().equals(email)) {
        System.out.println("Student with email " + email + " belongs to faculty " + faculty.getName() + ".");
        return;
      }
    }

    System.out.println("Student with email " + email + " does not belong to faculty " + faculty.getName() + ".");
  }
}