package lab1.src.store;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import lab1.src.menu.*;
import lab1.src.university.*;

public class FileManager {
  private static final String FACULTIES_FILE_PATH = MenuConstants.FOLDER_PATH + "faculties.txt";

  public static University loadUniversityData() {
    University university = new University();
    try (BufferedReader facultiesReader = new BufferedReader(new FileReader(FACULTIES_FILE_PATH))) {
      String line;
      while ((line = facultiesReader.readLine()) != null) {
        String[] facultyData = line.split(",");
        String facultyName = facultyData[0].trim();
        String facultyAbbreviation = facultyData[1].trim();
        StudyField studyField = StudyField.valueOf(facultyData[2].trim());

        ArrayList<Student> students = new ArrayList<Student>();
        try (BufferedReader studentsReader = new BufferedReader(
            new FileReader(MenuConstants.FOLDER_PATH + facultyAbbreviation + ".txt"))) {
          String studentLine;
          while ((studentLine = studentsReader.readLine()) != null) {
            String[] studentData = studentLine.split(",");
            String firstName = studentData[0].trim();
            String lastName = studentData[1].trim();
            String email = studentData[2].trim();
            LocalDate enrollmentDate = LocalDate.parse(studentData[3].trim());
            LocalDate birthDate = LocalDate.parse(studentData[4].trim());
            boolean isGraduated = Boolean.parseBoolean(studentData[5].trim());
            Student student = new Student(firstName, lastName, email, enrollmentDate, birthDate, isGraduated);
            students.add(student);
          }
        }
        Faculty faculty = new Faculty(facultyName, facultyAbbreviation, students, studyField);
        for (Student student : students) {
          System.out.println(student.getFullName() + facultyName);
        }
        university.addFaculty(faculty);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return university;
  }

  public static void saveUniversityData(University university) {
    try {
      FileWriter facultiesFile = new FileWriter(FACULTIES_FILE_PATH);
      for (Faculty faculty : university.getFaculties()) {
        facultiesFile
            .write(faculty.getName() + "," + faculty.getAbbreviation() + "," + faculty.getStudyField() + "\n");
        FileWriter studentsFile = new FileWriter(MenuConstants.FOLDER_PATH + faculty.getAbbreviation() + ".txt");
        for (Student student : faculty.getStudents()) {
          studentsFile
              .write(student.getFirstName() + "," + student.getLastName() + "," + student.getEmail() + ","
                  + student.getEnrollmentDate() + "," + student.getBirthDate() + "," + student.getIsGraduated() + "\n");
        }
        studentsFile.close();
      }
      facultiesFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
