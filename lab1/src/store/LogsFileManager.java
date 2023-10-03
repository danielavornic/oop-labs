package lab1.src.store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lab1.src.logger.Log;
import lab1.src.menu.MenuConstants;

public class LogsFileManager {
  private static final String LOGS_FILE_PATH = MenuConstants.FOLDER_PATH + "logs.txt";
  private static final String TEMP_LOGS_FILE_PATH = MenuConstants.FOLDER_PATH + "tempLogs.txt";

  public static ArrayList<Log> loadLogs() {
    ArrayList<Log> logs = new ArrayList<Log>();
    try (BufferedReader logsReader = new BufferedReader(new FileReader(LOGS_FILE_PATH))) {
      String line;
      while ((line = logsReader.readLine()) != null) {
        String[] logData = line.split(",");
        String timestamp = logData[0].trim();
        String username = logData[1].trim();
        String action = logData[2].trim();
        Log log = new Log(timestamp, action, username);
        logs.add(0, log);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return logs;
  }

  public static void saveLog(Log log) {
    try {
      FileWriter tempFileWriter = new FileWriter(TEMP_LOGS_FILE_PATH);
      tempFileWriter.write(log.getTimestamp() + "," + log.getUsername() + "," + log.getAction() + "\n");

      BufferedReader fileReader = new BufferedReader(new FileReader(LOGS_FILE_PATH));
      String line;
      while ((line = fileReader.readLine()) != null) {
        tempFileWriter.write(line + "\n");
      }
      fileReader.close();
      tempFileWriter.close();

      File originalFile = new File(LOGS_FILE_PATH);
      File tempFile = new File(TEMP_LOGS_FILE_PATH);
      tempFile.renameTo(originalFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
