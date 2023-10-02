package lab1.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class FacultyMenu {
  private static Scanner scanner = new Scanner(System.in);
  private static University university = Menu.getUniversity();

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
    System.out.print("Enter the file path for batch enrollment: ");
    String filePath = scanner.nextLine();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
    System.out.print("Enter the file path for batch graduation: ");
    String filePath = scanner.nextLine();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
    for (Student student : faculty.getStudents()) {
      if (student.getEmail().equals(email)) {
        return student;
      }
    }
    return null;
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