package lab1.src.menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import lab1.src.Main;
import lab1.src.store.FileManager;
import lab1.src.university.*;

public class FacultyMenu {
  private static University university = Main.getUniversity();

  private static final String FACULTY_MENU_TITLE = "TUM SMS Faculty Operations";
  private static final String[] FACULTY_MENU_OPTIONS = {
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

  private static final String FOLDER_PATH = "/home/vornic/Uni/sem_3/oop/labs/lab1/temp/";
  private static final String ENROLLED_STUDENTS_PATH = FOLDER_PATH + "studentsToEnroll.txt";
  private static final String GRADUATED_STUDENTS_PATH = FOLDER_PATH + "studentsToGraduate.txt";

  public static void run() {
    MenuPrinter.displayMenuOptions(FACULTY_MENU_OPTIONS, FACULTY_MENU_TITLE);
    String input = InputHandler.getStringInput("Choose an option: ");
    switch (input) {
      case "1":
        createStudent();
        break;
      case "2":
        batchEnrollStudents();
        break;
      case "3":
        graduateStudent();
        break;
      case "4":
        batchGraduateStudents();
        break;
      case "5":
        displayStudents();
        break;
      case "6":
        displayGraduatedStudents();
        break;
      case "8":
        checkIfStudentBelongsToFaculty();
        break;
      case "b":
        GeneralMenu.run();
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
      MainMenu.run();
    }
  }

  public static void createStudent() {
    System.out.println("Creating student...");
    String firstName = InputHandler.getStringInput("First name: ");
    String lastName = InputHandler.getStringInput("Last name: ");
    String email = InputHandler.getStringInput("Email: ");
    LocalDate enrollmentDate = InputHandler.getDateInput("Enrollment date: ");
    LocalDate dateOfBirth = InputHandler.getDateInput("Date of birth: ");
    Faculty faculty = chooseFaculty();
    if (faculty != null) {
      Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
      faculty.getStudents().add(student);
      System.out.println("Student created successfully!");
    }
  }

  public static void batchEnrollStudents() {
    try (BufferedReader br = new BufferedReader(new FileReader(ENROLLED_STUDENTS_PATH))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] studentData = line.split(",");
        if (studentData.length >= 5) {
          String firstName = studentData[0].trim();
          String lastName = studentData[1].trim();
          String email = studentData[2].trim();
          LocalDate enrollmentDate = LocalDate.parse(studentData[3].trim());
          LocalDate dateOfBirth = LocalDate.parse(studentData[4].trim());
          String facultyAbbreviation = studentData[5].trim();
          Faculty faculty = university.getFacultyByAbbreviation(facultyAbbreviation);

          if (faculty != null) {
            Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
            faculty.getStudents().add(student);
            System.out.println("Enrolled student: " + firstName + " " + lastName);
          } else {
            System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
          }
        } else {
          System.out.println("Invalid data format in the file: " + line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void graduateStudent() {
    String email = InputHandler.getStringInput("Student email: ");
    Faculty faculty = university.getFacultyByStudentEmail(email);
    if (faculty == null) {
      System.out.println("Student with email " + email + " not found.");
    } else {
      Student student = findStudentByEmail(faculty, email);
      if (student != null) {
        if (!student.getIsGraduated()) {
          student.setIsGraduated(true);
          System.out.println("Student graduated successfully!");
        } else {
          System.out.println("Student with email " + email + " has already graduated.");
        }
      } else {
        System.out.println("Student with email " + email + " not found.");
      }
    }
  }

  public static void batchGraduateStudents() {
    try (BufferedReader br = new BufferedReader(new FileReader(GRADUATED_STUDENTS_PATH))) {
      String line;
      while ((line = br.readLine()) != null) {
        String email = line.trim();
        Student student = Faculty.getStudentByEmail(email);
        if (student != null) {
          if (!student.getIsGraduated()) {
            student.setIsGraduated(true);
            System.out.println("Graduated student: " + student.getFullName());
          } else {
            System.out.println("Student with email " + email + " has already graduated.");
          }
        } else {
          System.out.println("Student with email " + email + " not found.");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void displayStudents() {
    university.displayStudents();
  }

  public static void displayGraduatedStudents() {
    university.displayGraduatedStudents();
  }

  public static void checkIfStudentBelongsToFaculty() {
    String email = InputHandler.getStringInput("Student email: ");

    university.displayFaculties();
    int facultyIndex = InputHandler.getInputInt("Faculty index: ") - 1;
    while (facultyIndex < 0 || facultyIndex >= university.getFaculties().size()) {
      System.out.println("Invalid faculty index. Please choose a valid index.");
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

  private static Student findStudentByEmail(Faculty faculty, String email) {
    return faculty.getStudents().stream().filter(student -> student.getEmail().equals(email)).findFirst().orElse(null);
  }

  private static Faculty chooseFaculty() {
    System.out.println("Faculties: ");
    for (int i = 0; i < university.getFaculties().size(); i++) {
      System.out.println((i + 1) + ") " + university.getFaculties().get(i).getName() + " ("
          + university.getFaculties().get(i).getAbbreviation() + ")");
    }
    int facultyIndex = InputHandler.getInputInt("Faculty index: ") - 1;
    if (facultyIndex >= 0 && facultyIndex < university.getFaculties().size()) {
      return university.getFaculties().get(facultyIndex);
    } else {
      System.out.println("Invalid faculty index. Please choose a valid index.");
      return null;
    }
  }
}