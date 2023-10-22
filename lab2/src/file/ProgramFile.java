package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProgramFile extends SystemFile {
  private int lineCount;
  private int classCount;
  private int methodCount;

  public ProgramFile(String directoryPath, String filename, String extension) {
    super(directoryPath, filename, extension);
    parseProgramFile();
  }

  private void parseProgramFile() {
    File file = new File(directoryPath + File.separator + filename + "." + extension);
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
          if (line.trim().startsWith("class "))
            classCount++;
          // Simplified; assumes public methods, no nested classes, etc.
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

  public int getLineCount() {
    return lineCount;
  }

  public int getClassCount() {
    return classCount;
  }

  public int getMethodCount() {
    return methodCount;
  }

  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
  }

  public void setClassCount(int classCount) {
    this.classCount = classCount;
  }

  public void setMethodCount(int methodCount) {
    this.methodCount = methodCount;
  }
}
