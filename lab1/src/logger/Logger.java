package lab1.src.logger;

import java.time.LocalDateTime;
import java.util.List;

import lab1.src.store.LogsFileManager;
import lab1.src.university.Faculty;
import lab1.src.university.Student;

public class Logger {
  private String username;
  private List<Log> logs;

  public Logger(String username) {
    this.username = username;
    this.logs = LogsFileManager.loadLogs();
  }

  public void log(String action) {
    String timestamp = LocalDateTime.now().toString();
    Log log = new Log(timestamp, action, username);
    logs.add(0, log);
    LogsFileManager.saveLog(log);
  }

  public void logStudentCreation(Student student, String facultyAbbreviation) {
    log("Created student " + student.getFullName() + " (" + student.getEmail() + ") in faculty "
        + facultyAbbreviation + ".");
  }

  public void logStudentGraduation(Student student, String facultyAbbreviation) {
    log("Graduated student " + student.getFullName() + " (" + student.getEmail() + ") in faculty "
        + facultyAbbreviation + ".");
  }

  public void logFacultyCreation(Faculty faculty) {
    log("Created faculty " + faculty.getName() + " (" + faculty.getAbbreviation() + ").");
  }
}
