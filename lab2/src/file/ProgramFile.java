package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProgramFile extends SystemFile {
  private int lineCount;
  private int classCount;
  private int methodCount;

  public ProgramFile(String directoryPath, String filename, long lastModifiedTime) {
    super(directoryPath, filename, lastModifiedTime);
    parseProgramFile();
  }

  private void parseProgramFile() {
    File file = new File(directoryPath + File.separator + filename);
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lineCount++;

        if (extension.equals("py")) {
          if (line.trim().startsWith("class "))
            classCount++;
          if (line.trim().startsWith("def "))
            methodCount++;
        } else if (extension.equals("java")) {
          if (line.trim().contains("class "))
            classCount++;
          // Simplified; assumes public methods, no nested classes, etc.
          // TODO: improve class method detection
          if (line.trim().contains("public "))
            methodCount++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Line count: " + lineCount);
    System.out.println("Class count: " + classCount);
    System.out.println("Method count: " + methodCount);
  }
}
