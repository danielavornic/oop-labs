package lab1.src.store;

import java.io.IOException;
import java.util.ArrayList;

import lab1.src.university.Faculty;
import lab1.src.university.Student;
import lab1.src.university.StudyField;
import lab1.src.university.University;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class FileManager {
  private static final String FOLDER_PATH = "/home/vornic/Uni/sem_3/oop/labs/lab1/temp/";
  private static final String FACULTIES_FILE_PATH = FOLDER_PATH + "faculties.txt";

  public static University loadUniversityData() {
    University university = new University();
    try (BufferedReader facultiesReader = new BufferedReader(new FileReader(FACULTIES_FILE_PATH))) {
      String line;
      while ((line = facultiesReader.readLine()) != null) {
        String[] facultyData = line.split(",");
        String facultyName = facultyData[0].trim();
        String facultyAbbreviation = facultyData[1].trim();
        StudyField studyField = StudyField.valueOf(facultyData[2].trim());
        Faculty faculty = new Faculty(facultyName, facultyAbbreviation, new ArrayList<>(), studyField);
        university.addFaculty(faculty);

        // Read students data from faculty file
        try (BufferedReader studentsReader = new BufferedReader(
            new FileReader(FOLDER_PATH + facultyAbbreviation + ".txt"))) {
          String studentLine;
          while ((studentLine = studentsReader.readLine()) != null) {
            String[] studentData = studentLine.split(",");
            String firstName = studentData[0].trim();
            String lastName = studentData[1].trim();
            String email = studentData[2].trim();
            LocalDate enrollmentDate = LocalDate.parse(studentData[3].trim());
            LocalDate birthDate = LocalDate.parse(studentData[4].trim());
            Student student = new Student(firstName, lastName, email, enrollmentDate, birthDate);
            faculty.addStudent(student);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    university.displayFaculties();
    return university;
  }

  public static void saveUniversityData(University university) {
    try {
      FileWriter facultiesFile = new FileWriter(FACULTIES_FILE_PATH);
      for (Faculty faculty : university.getFaculties()) {
        facultiesFile
            .write(faculty.getName() + "," + faculty.getAbbreviation() + "," + faculty.getStudyField() + "\n");
        FileWriter studentsFile = new FileWriter(FOLDER_PATH + faculty.getAbbreviation() + ".txt");
        for (Student student : faculty.getStudents()) {
          studentsFile
              .write(student.getFirstName() + "," + student.getLastName() + "," + student.getEmail() + ","
                  + student.getEnrollmentDate() + "," + student.getBirthDate() + "\n");
        }
        studentsFile.close();
      }
      facultiesFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
